public Span toSpan(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    // decorating here addresses join, new traces or children and ad-hoc trace contexts
    TraceContext decorated = propagationFactory.decorate(context);
    if (isNoop(decorated)) return new NoopSpan(decorated);
    // allocate a mutable span in case multiple threads call this method.. they'll use the same data
    PendingSpan pendingSpan = pendingSpans.getOrCreate(decorated);
    return new RealSpan(decorated,
        pendingSpans,
        pendingSpan.state(),
        pendingSpan.clock(),
        spanReporter,
        errorParser
    );
  }