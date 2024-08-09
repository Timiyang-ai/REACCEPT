public static TraceId fromLowerBase16(CharSequence src) {
    Utils.checkArgument(
        src.length() == BASE16_SIZE,
        "Invalid size: expected %s, got %s",
        BASE16_SIZE,
        src.length());
    return new TraceId(
        BigendianEncoding.longFromBase16String(src, 0),
        BigendianEncoding.longFromBase16String(src, BigendianEncoding.LONG_BASE16));
  }