public synchronized List<List<Span>> getTraces() {
    List<List<Span>> result = new ArrayList<>();
    for (String lowTraceId : traceIdToTraceIdTimeStamps.keySet()) {
      List<Span> sameTraceId = spansByTraceId(lowTraceId);
      if (strictTraceId) {
        result.addAll(strictByTraceId(sameTraceId));
      } else {
        result.add(sameTraceId);
      }
    }
    return result;
  }