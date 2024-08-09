@Deprecated @Override public SpanCustomizer annotate(long timestamp, String value) {
    return tracer.currentSpanCustomizer().annotate(timestamp, value);
  }