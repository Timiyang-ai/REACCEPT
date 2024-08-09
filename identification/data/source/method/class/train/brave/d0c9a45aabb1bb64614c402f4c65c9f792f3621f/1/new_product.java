@Nullable public Span next() {
    Tracer tracer = tracer();
    if (tracer == null) return null;
    Span next = tracer.nextSpan();
    SpanAndScope spanAndScope = SpanAndScope.create(next, tracer.withSpanInScope(next));
    currentSpanInScope.get().addFirst(spanAndScope);
    return next;
  }