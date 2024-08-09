public SpanCustomizer currentSpanCustomizer() {
    TraceContext currentContext = currentTraceContext.get();
    return currentContext != null && Boolean.TRUE.equals(currentContext.sampled())
        ? RealSpanCustomizer.create(currentContext, recorder) : NoopSpanCustomizer.INSTANCE;
  }