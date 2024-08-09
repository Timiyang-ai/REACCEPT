@Override public SpanCustomizer annotate(String value) {
    Span currentSpan = tracer.currentSpan();
    if (currentSpan != null) {
      currentSpan.annotate(value);
    }
    return this;
  }