@Nullable public Span currentSpan() {
    TraceContext currentContext = currentTraceContext.get();
    if (currentContext == null) return null;
    TraceContext decorated = decorateExternal(currentContext);
    if (isNoop(decorated)) return new NoopSpan(decorated);

    // Returns a lazy span to reduce overhead when tracer.currentSpan() is invoked just to see if
    // one exists, or when the result is never used.
    return new LazySpan(this, decorated);
  }