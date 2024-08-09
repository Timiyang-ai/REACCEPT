public synchronized List<List<Span>> getTraces() {
    List<List<Span>> result = new ArrayList<>();
    for (long traceId : traceIdToTraceIdTimeStamps.keySet()) {
      List<Span> sameTraceId = spansByTraceId(traceId);
      if (strictTraceId) {
        result.addAll(strictByTraceId(sameTraceId));
      } else {
        result.add(sameTraceId);
      }
    }
    return result;
  }