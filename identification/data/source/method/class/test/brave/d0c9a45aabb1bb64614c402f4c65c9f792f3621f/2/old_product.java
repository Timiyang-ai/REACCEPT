@Nullable public Span next(TraceContextOrSamplingFlags extracted) {
    Tracer tracer = tracer();
    if (tracer == null) return null;
    Span next = tracer.nextSpan(extracted);
    SpanAndScope spanAndScope = SpanAndScope.create(next, tracer.withSpanInScope(next));
    currentSpanInScope.get().addFirst(spanAndScope);
    return next;
  }