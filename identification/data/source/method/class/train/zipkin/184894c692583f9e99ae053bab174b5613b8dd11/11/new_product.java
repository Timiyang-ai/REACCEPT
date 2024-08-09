@Override
  public Call<List<List<Span>>> getTraces(QueryRequest request) {
    return strictTraceId ? doGetTraces(request) :
      doGetTraces(request).map(input -> {
        // Due to tokenization of the trace ID, our matches are imprecise on span.trace_id_high
        input.removeIf(next -> next.get(0).traceId().length() > 16 && !request.test(next));
        return input;
      });
  }