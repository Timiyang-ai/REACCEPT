public SpanCustomizer currentSpanCustomizer() {
    // note: we don't need to decorate the context for propagation as it is only used for toString
    TraceContext context = currentTraceContext.get();
    if (context == null || isNoop(context)) return NoopSpanCustomizer.INSTANCE;
    PendingSpanRecord pendingSpanRecord = pendingSpanRecords.getOrCreate(context);
    return new RealSpanCustomizer(context, pendingSpanRecord.span(), pendingSpanRecord.clock());
  }