public static SpanId fromLowerBase16(CharSequence src) {
    Utils.checkArgument(
        src.length() == HEX_SIZE, "Invalid size: expected %s, got %s", HEX_SIZE, src.length());
    return new SpanId(LowerCaseBase16Encoding.decodeToBytes(src));
  }