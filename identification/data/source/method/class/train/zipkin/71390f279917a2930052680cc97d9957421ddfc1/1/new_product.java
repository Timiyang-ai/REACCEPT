public static Mapper<List<List<Span>>, List<List<Span>>> filterTraces(Iterable<String> traceIds) {
    return new FilterTracesByIds(traceIds);
  }