public List<List<Span>> getTraces() {
    List<Long> traceIds = storage.spanStore().traceIds();
    List<List<Span>> result = new ArrayList<>(traceIds.size());
    for (long traceId : traceIds) {
      result.add(storage.spanStore().getTrace(traceId));
    }
    return result;
  }