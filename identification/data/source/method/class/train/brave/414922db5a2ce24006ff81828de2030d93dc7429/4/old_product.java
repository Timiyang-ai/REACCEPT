@Override public SpanCustomizer name(String name) {
    Span currentSpan = tracer.currentSpan();
    if (currentSpan != null) {
      currentSpan.name(name);
    }
    return this;
  }