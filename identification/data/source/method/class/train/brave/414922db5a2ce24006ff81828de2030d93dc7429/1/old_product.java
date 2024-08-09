@Override public SpanCustomizer tag(String key, String value) {
    Span currentSpan = tracer.currentSpan();
    if (currentSpan != null) {
      currentSpan.tag(key, value);
    }
    return this;
  }