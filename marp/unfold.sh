#!/usr/bin/env bash
set -euo pipefail

usage() {
  cat <<'EOF'
Usage: unfold.sh <folder>

Combines Marp slide Markdown files in <folder> into a single Markdown file:
  <folder>/<folder_basename>.md

The file processing order is taken from <folder>/README.md (first occurrence
order of referenced .md files). If no .md references are found, falls back to
all .md files (excluding README.md) sorted by name.
EOF
}

if [[ ${1:-} == "-h" || ${1:-} == "--help" ]]; then
  usage
  exit 0
fi

if [[ $# -ne 1 ]]; then
  usage >&2
  exit 2
fi

INPUT_DIR="$1"
if [[ ! -d "$INPUT_DIR" ]]; then
  echo "Error: not a folder: $INPUT_DIR" >&2
  exit 1
fi

# Resolve to an absolute path.
INPUT_DIR="$(cd "$INPUT_DIR" && pwd)"
README="$INPUT_DIR/README.md"
if [[ ! -f "$README" ]]; then
  echo "Error: missing README.md in: $INPUT_DIR" >&2
  exit 1
fi

OUT_FILE="$INPUT_DIR/$(basename "$INPUT_DIR").md"

python3 - "$INPUT_DIR" "$OUT_FILE" <<'PY'
import base64
import os
import re
import sys
import zlib


INPUT_DIR = sys.argv[1]
OUT_FILE = sys.argv[2]


def _read_text(path: str) -> str:
    with open(path, "r", encoding="utf-8", errors="replace") as f:
        return f.read().replace("\r\n", "\n").replace("\r", "\n")


def _kroki_plantuml_svg_url(plantuml_source: str) -> str:
    # Generado por GPT-5.2
    # Kroki expects: base64url(raw-deflate(utf-8(source))) without padding.
    # data = plantuml_source.encode("utf-8")
    # co = zlib.compressobj(level=9, method=zlib.DEFLATED, wbits=-zlib.MAX_WBITS)
    # raw_deflate = co.compress(data) + co.flush()
    # enc = base64.urlsafe_b64encode(raw_deflate).decode("ascii").rstrip("=")
    # Arreglado según se explica en https://kroki.io/#how
    enc = base64.urlsafe_b64encode(zlib.compress(plantuml_source.encode('utf-8'), 9)).decode('ascii').rstrip("=")
    return f"https://kroki.io/plantuml/svg/{enc}"


def _plantuml_embed_markdown(plantuml_source: str) -> str:
    src = plantuml_source.strip("\n")
    url = _kroki_plantuml_svg_url(src)
    return (
        f"![PlantUML diagram]({url})\n\n"
        f"<details>\n"
        f"<summary>PlantUML source</summary>\n\n"
        f"```plantuml\n{src}\n```\n\n"
        f"</details>"
    )


def _looks_like_markdown(s: str) -> bool:
    # Heuristic: if it already contains common Markdown structure, don't reformat.
    md_markers = [
        "```",
        "# ",
        "## ",
        "- ",
        "* ",
        "1. ",
        "[",
        "](",
        "**",
        "_",
        "> ",
        "|",
    ]
    return any(m in s for m in md_markers)


def _strip_outer_blank_lines(s: str) -> str:
    lines = s.split("\n")
    while lines and not lines[0].strip():
        lines.pop(0)
    while lines and not lines[-1].strip():
        lines.pop()
    return "\n".join(lines)


def _extract_ordered_md_files_from_readme(readme_text: str, folder: str) -> list[str]:
    # Extract .md references in order of appearance.
    # - Markdown links: (path/to/file.md)
    # - Plain references: path/to/file.md
    candidates: list[str] = []

    link_pat = re.compile(r"\(([^)]+?\.md)\)")
    plain_pat = re.compile(r"(?<![\w/.-])([\w./-]+?\.md)(?![\w/.-])")

    for m in link_pat.finditer(readme_text):
        candidates.append(m.group(1))
    for m in plain_pat.finditer(readme_text):
        candidates.append(m.group(1))

    out: list[str] = []
    seen: set[str] = set()
    for raw in candidates:
        raw = raw.strip()
        if not raw or "://" in raw:
            continue

        # Drop anchors (README.md#section -> README.md)
        raw = raw.split("#", 1)[0]

        # Normalize paths.
        rel = os.path.normpath(raw)
        # README may list files relative to the folder root; allow both.
        p1 = os.path.join(folder, rel)
        p2 = os.path.join(folder, os.path.basename(rel))
        path = p1 if os.path.isfile(p1) else p2 if os.path.isfile(p2) else None
        if not path:
            continue

        # Only consider markdown files in the folder subtree.
        ap = os.path.abspath(path)
        if not ap.startswith(os.path.abspath(folder) + os.sep):
            continue

        bn = os.path.basename(ap)
        if bn.lower() == "readme.md":
            continue

        if ap in seen:
            continue
        seen.add(ap)
        out.append(ap)

    return out


def _fallback_md_files(folder: str) -> list[str]:
    files = []
    for name in os.listdir(folder):
        if not name.lower().endswith(".md"):
            continue
        if name.lower() == "readme.md":
            continue
        files.append(os.path.join(folder, name))
    return sorted(files)


def _parse_yaml_front_matter(text: str):
    # Returns (front_matter_text_or_None, rest_text)
    if not text.startswith("---\n"):
        return None, text

    lines = text.split("\n")
    # Must start with the opening fence on the first line.
    if lines[0].strip() != "---":
        return None, text
    # Find the next fence line.
    for i in range(1, len(lines)):
        if lines[i].strip() == "---":
            fm = "\n".join(lines[1:i]).rstrip("\n")
            rest = "\n".join(lines[i + 1 :]).lstrip("\n")
            return fm, rest
    return None, text


def _extract_title_from_front_matter(fm: str) -> str | None:
    # Very small YAML subset; avoid dependencies.
    for line in fm.split("\n"):
        if re.match(r"^\s*title\s*:\s*", line):
            v = line.split(":", 1)[1].strip()
            # Strip surrounding quotes.
            if (v.startswith("\"") and v.endswith("\"")) or (v.startswith("'") and v.endswith("'")):
                v = v[1:-1]
            v = v.strip()
            return v or None
    return None


def _extract_first_heading(text: str) -> str | None:
    for line in text.split("\n"):
        m = re.match(r"^\s*#\s+(.+?)\s*$", line)
        if m:
            return m.group(1).strip()
    return None


def _slugify_github(text: str) -> str:
    s = text.strip().lower()
    s = re.sub(r"[^\w\s-]", "", s)
    s = re.sub(r"\s+", "-", s)
    s = re.sub(r"-{2,}", "-", s)
    return s.strip("-")


def _split_fenced_code_blocks(text: str):
    # Split into segments where even indices are outside fenced code blocks.
    # This is a lightweight splitter for triple-backtick fences.
    parts = []
    i = 0
    fence_pat = re.compile(r"(^```[^\n]*\n)", flags=re.M)
    while True:
        m = fence_pat.search(text, i)
        if not m:
            parts.append((False, text[i:]))
            break
        # Outside segment.
        parts.append((False, text[i:m.start()]))
        # Find closing fence.
        close = re.search(r"^```\s*$", text[m.end():], flags=re.M)
        if not close:
            # Unterminated; treat the rest as outside.
            parts.append((False, text[m.start():]))
            break
        block_start = m.start()
        block_end = m.end() + close.end()
        parts.append((True, text[block_start:block_end]))
        i = block_end
    return parts


def _convert_plantuml_at_blocks(text: str) -> str:
    # Convert @startuml..@enduml blocks into GitHub-renderable PlantUML images.
    pat = re.compile(r"@startuml[^\n]*\n.*?\n@enduml\b", flags=re.S | re.I)

    def repl(m):
        return _plantuml_embed_markdown(m.group(0))

    return pat.sub(repl, text)


def _convert_plantuml_fenced_block(block: str) -> str:
    # If a fenced code block is explicitly plantuml/puml, convert it.
    # Otherwise, convert only if the fence body contains *only* an
    # @startuml..@enduml block.
    m = re.match(r"^```(?P<lang>[^\n]*)\n(?P<body>.*)\n```\s*$", block, flags=re.S)
    if not m:
        return block

    lang = (m.group("lang") or "").strip().lower().split()[0] if m.group("lang") is not None else ""
    body = m.group("body").rstrip("\n")

    if lang in {"plantuml", "puml"}:
        return _plantuml_embed_markdown(body)

    body_stripped = body.strip()
    if re.match(r"^@startuml[^\n]*\n.*\n@enduml\b\s*$", body_stripped, flags=re.S | re.I):
        return _plantuml_embed_markdown(body_stripped)

    return block


def _rewrite_marp_images(text: str) -> str:
    # Turn Marp background/size directives in image alt text into normal alt text.
    # Examples:
    #   ![bg right:40%](img.png) -> ![Background image](img.png)
    #   ![w:600](img.png) -> ![](img.png)
    img_pat = re.compile(r"!\[(?P<alt>[^\]]*)\]\((?P<url>[^)]+)\)")

    def repl(m):
        alt = m.group("alt").strip()
        url = m.group("url")
        if not alt:
            return m.group(0)

        alt_l = alt.lower()
        if alt_l.startswith("bg"):
            return f"![Background image]({url})"
        directive_prefixes = ("w:", "h:", "fit", "pos:", "opacity:", "blur:")
        if alt_l.startswith(directive_prefixes):
            return f"![]({url})"

        # Common combined patterns like: "bg right:40%" handled above; if it
        # looks directive-heavy, still keep the URL.
        if any(tok in alt_l for tok in ["bg ", " right", " left", " top", " bottom", ":"]):
            return f"![]({url})"

        return m.group(0)

    return img_pat.sub(repl, text)


def _process_html_comments_outside_code(text: str) -> str:
    # Remove Marp directive comments, convert backgroundImage directives to images,
    # and reformat explanatory comments into Markdown-looking content.
    comment_pat = re.compile(r"<!--(.*?)-->", flags=re.S)

    def is_marp_directive(body: str) -> bool:
        b = body.strip()
        if not b:
            return True
        # Single-line directive styles.
        if re.match(r"^_?[a-zA-Z][\w-]*\s*:\s*.*$", b):
            key = b.split(":", 1)[0].strip().lstrip("_").lower()
            marp_keys = {
                "marp",
                "theme",
                "paginate",
                "size",
                "class",
                "headingdivider",
                "header",
                "footer",
                "style",
                "transition",
                "background",
                "backgroundcolor",
                "backgroundimage",
                "backgroundposition",
                "backgroundsize",
                "backgroundrepeat",
                "color",
                "math",
            }
            return key in marp_keys
        # Multi-line directives: one directive per line.
        lines = [ln.strip() for ln in b.split("\n") if ln.strip()]
        if lines and all(re.match(r"^_?[a-zA-Z][\w-]*\s*:\s*", ln) for ln in lines):
            keys = {ln.split(":", 1)[0].strip().lstrip("_").lower() for ln in lines}
            marp_keys = {
                "marp",
                "theme",
                "paginate",
                "size",
                "class",
                "headingdivider",
                "header",
                "footer",
                "style",
                "transition",
                "background",
                "backgroundcolor",
                "backgroundimage",
                "backgroundposition",
                "backgroundsize",
                "backgroundrepeat",
                "color",
                "math",
            }
            if keys & marp_keys:
                return True
        return False

    def background_image_from_directive(body: str) -> str | None:
        b = body.strip()
        m = re.match(r"^_?backgroundImage\s*:\s*(.*)$", b, flags=re.I)
        if not m:
            return None
        rhs = m.group(1).strip().rstrip(";")
        # Allow: url('...'), url("..."), plain path
        mu = re.match(r"^url\((.*)\)\s*$", rhs, flags=re.I)
        if mu:
            rhs = mu.group(1).strip()
        if (rhs.startswith("\"") and rhs.endswith("\"")) or (rhs.startswith("'") and rhs.endswith("'")):
            rhs = rhs[1:-1]
        rhs = rhs.strip()
        if not rhs:
            return None
        return f"![Background image]({rhs})"

    def repl(m):
        body = m.group(1)

        # Convert backgroundImage directive to a normal image reference.
        bg = background_image_from_directive(body)
        if bg is not None:
            return bg

        if is_marp_directive(body):
            return ""

        content = _strip_outer_blank_lines(body)
        if not content:
            return ""

        if not _looks_like_markdown(content):
            content = f">[!NOTE]\n>{content}"

        return content.rstrip("\n") + "\n"

    return comment_pat.sub(repl, text)


def _cleanup_whitespace(text: str) -> str:
    # Avoid excessive blank lines introduced by removals.
    text = re.sub(r"\n{4,}", "\n\n\n", text)
    # Trim trailing spaces.
    text = "\n".join([ln.rstrip() for ln in text.split("\n")])
    return text.strip() + "\n"


def _remove_slide_separators(text: str) -> str:
    # Remove Marp slide separators (---) outside code blocks.
    lines = []
    for ln in text.split("\n"):
        if re.match(r"^\s*---\s*$", ln):
            continue
        lines.append(ln)
    return "\n".join(lines)


def _remove_style_blocks_outside_code(text: str) -> str:
    # GitHub sanitizes <style> anyway; remove to keep plain Markdown.
    return re.sub(r"<style\b[^>]*>.*?</style>", "", text, flags=re.S | re.I)


def _normalize_inline_html_outside_code(text: str) -> str:
    # Convert a few Marp-specific/slide-specific HTML constructs to plain Markdown.
    text = re.sub(r"<\s*emph\s*>(.*?)<\s*/\s*emph\s*>", r"**\1**", text, flags=re.S | re.I)
    return text


def _process_one_file(path: str) -> str:
    raw = _read_text(path)
    fm, rest = _parse_yaml_front_matter(raw)
    title = _extract_title_from_front_matter(fm) if fm else None

    text = rest

    # Work outside code blocks for PlantUML blocks, comments and image rewrites.
    parts = _split_fenced_code_blocks(text)
    out = []
    for is_code, seg in parts:
        if is_code:
            out.append(_convert_plantuml_fenced_block(seg))
        else:
            seg = _convert_plantuml_at_blocks(seg)
            seg = _remove_slide_separators(seg)
            seg = _remove_style_blocks_outside_code(seg)
            seg = _normalize_inline_html_outside_code(seg)
            seg = _rewrite_marp_images(seg)
            seg = _process_html_comments_outside_code(seg)
            out.append(seg)
    text = "".join(out)

    # If we have a title in front matter and the content does not start with a heading,
    # add a top heading.
    if title and not re.match(r"^\s*#\s+", text):
        text = f"# {title}\n\n" + text.lstrip("\n")

    return _cleanup_whitespace(text)


readme_text = _read_text(os.path.join(INPUT_DIR, "README.md"))
ordered = _extract_ordered_md_files_from_readme(readme_text, INPUT_DIR)
if not ordered:
    ordered = _fallback_md_files(INPUT_DIR)

out_chunks = []

toc_entries = []
slug_counts: dict[str, int] = {}
for path in ordered:
    raw = _read_text(path)
    fm, rest = _parse_yaml_front_matter(raw)
    title = _extract_title_from_front_matter(fm) if fm else None
    if not title:
        title = _extract_first_heading(rest)
    if not title:
        title = os.path.splitext(os.path.basename(path))[0]

    slug = _slugify_github(title)
    if not slug:
        slug = "section"
    if slug in slug_counts:
        slug_counts[slug] += 1
        slug = f"{slug}-{slug_counts[slug]}"
    else:
        slug_counts[slug] = 0

    toc_entries.append((title, slug))

out_chunks.append("## Índice\n\n")
for title, slug in toc_entries:
    out_chunks.append(f"- [{title}](#{slug})\n")
out_chunks.append("\n")

for idx, path in enumerate(ordered):
    rel = os.path.relpath(path, INPUT_DIR)
    out_chunks.append(f"<!-- Source: {rel} -->\n")
    out_chunks.append(_process_one_file(path))

tmp = OUT_FILE + ".tmp"
with open(tmp, "w", encoding="utf-8", newline="\n") as f:
    f.write("".join(out_chunks).rstrip("\n") + "\n")
os.replace(tmp, OUT_FILE)

PY

echo "Wrote: $OUT_FILE"
