@Override public SpanCustomizer annotate(long timestamp, String value) {
    Span currentSpan = tracer.currentSpan();
    if (currentSpan != null) {
      currentSpan.annotate(timestamp, value);
    }
    return this;
  }