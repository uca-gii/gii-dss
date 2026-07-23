#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

usage() {
    echo "Usage: $0 <file.puml> [output.png]" >&2
    exit 1
}

[[ $# -lt 1 ]] && usage

PUML_FILE="$1"
OUTPUT="${2:-${PUML_FILE%.puml}.png}"

[[ -f "$PUML_FILE" ]] || { echo "Error: file not found: $PUML_FILE" >&2; exit 1; }

URL=$(python3 "$SCRIPT_DIR/puml_render.py" "$PUML_FILE")

echo "Fetching: $URL" >&2
curl -fsSL "$URL" -o "$OUTPUT"
echo "Saved: $OUTPUT"