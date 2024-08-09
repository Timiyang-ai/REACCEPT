public static SpanId fromLowerBase16(CharSequence src) {
    Utils.checkArgument(
        src.length() == BASE16_SIZE,
        "Invalid size: expected %s, got %s",
        BASE16_SIZE,
        src.length());
    return new SpanId(BigendianEncoding.longFromBase16String(src, 0));
  }