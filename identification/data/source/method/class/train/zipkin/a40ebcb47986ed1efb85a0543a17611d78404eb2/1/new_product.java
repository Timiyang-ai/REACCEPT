public static String normalizeTraceId(String traceId) {
    if (traceId == null) throw new NullPointerException("traceId == null");
    int length = traceId.length();
    if (length == 0) throw new IllegalArgumentException("traceId is empty");
    if (length > 32) throw new IllegalArgumentException("traceId.length > 32");
    validateHex(traceId);
    if (length == 32 || length == 16) {
      return traceId;
    } else if (length < 16) {
      return padLeft(traceId, 16);
    } else {
      return padLeft(traceId, 32);
    }
  }