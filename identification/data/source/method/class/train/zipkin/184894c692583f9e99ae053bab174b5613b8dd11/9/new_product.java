@Override
  public Call<List<List<Span>>> getTraces(QueryRequest request) {
    if (!searchEnabled) return Call.emptyList();

    return strictTraceId ? doGetTraces(request) :
      doGetTraces(request).map(new FilterTraces(request));
  }