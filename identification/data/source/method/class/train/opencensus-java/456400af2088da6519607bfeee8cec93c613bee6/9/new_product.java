public static TraceId fromLowerBase16(CharSequence src) {
    Utils.checkNotNull(src, "src");
    Utils.checkArgument(
        src.length() == BASE16_SIZE,
        "Invalid size: expected %s, got %s",
        BASE16_SIZE,
        src.length());
    return fromLowerBase16(src, 0);
  }