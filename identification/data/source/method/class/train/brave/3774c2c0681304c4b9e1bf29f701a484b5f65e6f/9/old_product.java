public ScopedSpan startScopedSpanWithParent(String name, @Nullable TraceContext parent) {
    if (name == null) throw new NullPointerException("name == null");
    TraceContext context = propagationFactory.decorate(newContextBuilder(parent, sampler).build());
    CurrentTraceContext.Scope scope = currentTraceContext.newScope(context);
    if (isNoop(context)) return new NoopScopedSpan(context, scope);
    PendingSpan pendingSpan = pendingSpans.getOrCreate(context);
    Clock clock = pendingSpan.clock();
    MutableSpan state = pendingSpan.state();
    state.name(name);
    state.startTimestamp(clock.currentTimeMicroseconds());
    return new RealScopedSpan(context, scope, state, clock, pendingSpans, spanReporter,
        errorParser);
  }