public static String toLowerHex(long v) {
    char[] data = new char[16];
    writeHexLong(data, 0, v);
    return new String(data);
  }