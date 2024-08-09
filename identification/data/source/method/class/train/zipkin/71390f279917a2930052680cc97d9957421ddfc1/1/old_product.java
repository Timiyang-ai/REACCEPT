public static Call.Mapper<List<List<Span>>, List<List<Span>>> filterTraces(
    Collection<String> traceIds) {
    return new FilterTracesByIds(traceIds);
  }