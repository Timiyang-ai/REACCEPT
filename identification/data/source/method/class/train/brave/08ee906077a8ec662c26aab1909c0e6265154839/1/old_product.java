public String traceIdString() {
    if (traceIdHigh() != 0) {
      char[] result = new char[32];
      writeHexLong(result, 0, traceIdHigh());
      writeHexLong(result, 16, traceId());
      return new String(result);
    }
    char[] result = new char[16];
    writeHexLong(result, 0, traceId());
    return new String(result);
  }