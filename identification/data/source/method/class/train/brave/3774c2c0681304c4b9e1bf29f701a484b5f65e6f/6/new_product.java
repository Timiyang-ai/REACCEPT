public ScopedSpan startScopedSpanWithParent(String name, @Nullable TraceContext parent) {
    if (name == null) throw new NullPointerException("name == null");
    if (parent == null) parent = currentTraceContext.get();
    TraceContext context =
      parent != null ? decorateContext(parent, parent.spanId(), 0L) : newRootContext();

    Scope scope = currentTraceContext.newScope(context);
    if (isNoop(context)) return new NoopScopedSpan(context, scope);

    PendingSpan pendingSpan = pendingSpans.getOrCreate(context, true);
    Clock clock = pendingSpan.clock();
    MutableSpan state = pendingSpan.state();
    state.name(name);
    return new RealScopedSpan(context, scope, state, clock, pendingSpans, finishedSpanHandler);
  }