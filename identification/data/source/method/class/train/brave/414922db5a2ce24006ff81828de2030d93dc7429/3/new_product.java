@Override public SpanCustomizer annotate(String value) {
    return tracer.currentSpanCustomizer().annotate(value);
  }