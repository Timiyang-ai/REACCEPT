public static SpanId fromLowerBase16(CharSequence src) {
    Utils.checkNotNull(src, "src");
    // TODO: Remove this extra condition.
    Utils.checkArgument(
        src.length() == BASE16_SIZE,
        "Invalid size: expected %s, got %s",
        BASE16_SIZE,
        src.length());
    return fromLowerBase16(src, 0);
  }