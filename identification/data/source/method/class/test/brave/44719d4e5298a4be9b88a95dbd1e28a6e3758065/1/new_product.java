public static String toLowerHex(long v) {
    char[] data = RecyclableBuffers.idBuffer();
    writeHexLong(data, 0, v);
    return new String(data, 0, 16);
  }