public List<List<Span>> getTraces() {
    return store.getTracesByIds(store.traceIds());
  }