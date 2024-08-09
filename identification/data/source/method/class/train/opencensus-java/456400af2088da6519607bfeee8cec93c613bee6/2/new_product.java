public static TraceId fromLowerBase16(CharSequence src) {
    Utils.checkArgument(
        src.length() == HEX_SIZE, "Invalid size: expected %s, got %s", HEX_SIZE, src.length());
    byte[] bytes = BaseEncoding.base16().lowerCase().decode(src);
    return new TraceId(bytes);
  }