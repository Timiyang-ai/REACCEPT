public List<List<Span>> getTraces() {
    List<Long> traceIds = store.traceIds();
    List<List<Span>> result = new ArrayList<>(traceIds.size());
    for (long traceId : traceIds) {
      result.add(store.getTrace(traceId));
    }
    return result;
  }