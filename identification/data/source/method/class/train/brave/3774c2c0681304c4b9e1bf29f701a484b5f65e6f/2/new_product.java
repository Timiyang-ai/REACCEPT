public ScopedSpan startScopedSpanWithParent(String name, @Nullable TraceContext parent) {
    if (name == null) throw new NullPointerException("name == null");
    TraceContext context = propagationFactory.decorate(newContextBuilder(parent, sampler).build());
    CurrentTraceContext.Scope scope = currentTraceContext.newScope(context);
    if (isNoop(context)) return new NoopScopedSpan(context, scope);
    PendingSpanRecord pendingSpanRecord = pendingSpanRecords.getOrCreate(context);
    Clock clock = pendingSpanRecord.clock();
    SpanRecord record = pendingSpanRecord.span();
    record.name(name);
    record.startTimestamp(clock.currentTimeMicroseconds());
    return new RealScopedSpan(context, scope, record, clock, pendingSpanRecords, spanReporter,
        errorParser);
  }