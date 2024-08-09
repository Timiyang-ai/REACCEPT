@Nullable public Span next() {
    Tracer tracer = tracer();
    if (tracer == null) return null;
    Span next = tracer.nextSpan();
    Object[] spanAndScope = {next, tracer.withSpanInScope(next)};
    getCurrentSpanInScopeStack().addFirst(spanAndScope);
    return next;
  }