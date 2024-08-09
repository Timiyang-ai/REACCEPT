public SpanCustomizer currentSpanCustomizer() {
    TraceContext currentContext = currentTraceContext.get();
    return currentContext != null
        ? RealSpanCustomizer.create(currentContext, recorder) : NoopSpanCustomizer.INSTANCE;
  }