public String traceIdString() {
    String r = traceIdString;
    if (r == null) {
      if (traceIdHigh != 0) {
        char[] result = RecyclableBuffers.idBuffer();
        writeHexLong(result, 0, traceIdHigh);
        writeHexLong(result, 16, traceId);
        r = new String(result);
      } else {
        r = toLowerHex(traceId);
      }
      traceIdString = r;
    }
    return r;
  }