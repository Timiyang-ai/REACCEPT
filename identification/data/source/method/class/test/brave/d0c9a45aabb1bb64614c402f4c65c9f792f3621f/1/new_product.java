@Nullable public Span next(TraceContextOrSamplingFlags extracted) {
    Tracer tracer = tracer();
    if (tracer == null) return null;
    Span next = tracer.nextSpan(extracted);
    Object[] spanAndScope = {next, tracer.withSpanInScope(next)};
    getCurrentSpanInScopeStack().addFirst(spanAndScope);
    return next;
  }