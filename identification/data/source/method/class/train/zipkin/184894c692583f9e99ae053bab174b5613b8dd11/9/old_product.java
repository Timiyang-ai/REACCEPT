@Override
  public Call<List<List<Span>>> getTraces(QueryRequest request) {
    return strictTraceId ? doGetTraces(request) :
      doGetTraces(request).map(new FilterTraces(request));
  }