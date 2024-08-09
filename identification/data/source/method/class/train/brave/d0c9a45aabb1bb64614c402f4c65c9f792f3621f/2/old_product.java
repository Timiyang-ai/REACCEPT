@Nullable public Span next() {
    Tracer tracer = tracer();
    if (tracer == null) return null;
    Span next = tracer.nextSpan();
    SpanAndScope spanAndScope = new SpanAndScope(next, tracer.withSpanInScope(next));
    currentSpanInScope.get().addFirst(spanAndScope);
    return next;
  }