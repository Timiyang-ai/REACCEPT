public static Call.Mapper<List<List<Span>>, List<List<Span>>> filterTraces(QueryRequest request) {
    return new FilterTracesByQuery(request);
  }