public List<List<Span>> getTraces() {
    List<List<zipkin.internal.v2.Span>> traces = storage.v2SpanStore().getTraces();
    List<List<Span>> result = new ArrayList<>(traces.size());
    for (List<zipkin.internal.v2.Span> trace2 : traces) {
      List<Span> sameTraceId = new ArrayList<>();
      for (zipkin.internal.v2.Span span2 : trace2) {
        sameTraceId.add(V2SpanConverter.toSpan(span2));
      }
      result.addAll(GroupByTraceId.apply(sameTraceId, false, false));
    }
    Collections.sort(result, TRACE_DESCENDING);
    return result;
  }