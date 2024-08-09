public static SpanId fromLowerBase16(String src) {
    checkArgument(
        src.length() == 2 * SIZE, "Invalid size: expected %s, got %s", 2 * SIZE, src.length());
    byte[] bytes = BaseEncoding.base16().lowerCase().decode(src);
    return new SpanId(bytes);
  }