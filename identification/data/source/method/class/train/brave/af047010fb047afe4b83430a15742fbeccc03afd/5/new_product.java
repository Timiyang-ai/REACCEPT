public SpanCustomizer currentSpanCustomizer() {
    TraceContext context = currentTraceContext.get();
    if (context == null || isNoop(context)) return NoopSpanCustomizer.INSTANCE;
    return new RealSpanCustomizer(context, recorder);
  }