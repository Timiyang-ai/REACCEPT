@Nullable public Span currentSpan() {
    TraceContext context = currentTraceContext.get();
    if (context == null) return null;
    if (!isDecorated(context)) { // It wasn't initialized by our tracer, so we must decorate.
      context = decorateContext(context, context.parentIdAsLong(), context.spanId());
    }
    if (isNoop(context)) return new NoopSpan(context);

    // Returns a lazy span to reduce overhead when tracer.currentSpan() is invoked just to see if
    // one exists, or when the result is never used.
    return new LazySpan(this, context);
  }