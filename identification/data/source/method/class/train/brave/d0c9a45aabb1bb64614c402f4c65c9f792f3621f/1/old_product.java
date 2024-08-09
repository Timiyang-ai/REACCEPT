@Nullable public Span next() {
    Tracer tracer = tracer();
    if (tracer == null) return null;
    Span next = tracer.nextSpan();
    currentSpanInScope.set(tracer.withSpanInScope(next));
    return next;
  }