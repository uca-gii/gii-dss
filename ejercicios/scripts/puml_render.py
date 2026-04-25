#!/usr/bin/env python3
import zlib
import sys


def encode6bit(b):
    if b < 10:
        return chr(48 + b)
    b -= 10
    if b < 26:
        return chr(65 + b)
    b -= 26
    if b < 26:
        return chr(97 + b)
    b -= 26
    if b == 0:
        return '-'
    if b == 1:
        return '_'
    return '?'


def plantuml_encode(text):
    compressed = zlib.compress(text.encode('utf-8'))[2:-4]  # raw deflate
    res = ''
    for i in range(0, len(compressed), 3):
        if i + 2 < len(compressed):
            b1, b2, b3 = compressed[i], compressed[i + 1], compressed[i + 2]
        elif i + 1 < len(compressed):
            b1, b2, b3 = compressed[i], compressed[i + 1], 0
        else:
            b1, b2, b3 = compressed[i], 0, 0
        res += encode6bit(b1 >> 2)
        res += encode6bit(((b1 & 0x3) << 4) | (b2 >> 4))
        res += encode6bit(((b2 & 0xF) << 2) | (b3 >> 6))
        res += encode6bit(b3 & 0x3F)
    return res


def main():
    if len(sys.argv) != 2:
        print(f"Usage: {sys.argv[0]} <file.puml>", file=sys.stderr)
        sys.exit(1)

    puml_file = sys.argv[1]
    with open(puml_file, 'r', encoding='utf-8') as f:
        uml = f.read()

    encoded = plantuml_encode(uml)
    url = f"https://www.plantuml.com/plantuml/png/{encoded}"
    print(url)


if __name__ == '__main__':
    main()
