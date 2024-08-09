public List<List<Span>> getTraces() {
    return storage.spanStore().getTraces(QueryRequest.builder().limit(Integer.MAX_VALUE).build());
  }