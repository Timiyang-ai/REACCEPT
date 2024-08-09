@Override public SpanCustomizer tag(String key, String value) {
    return tracer.currentSpanCustomizer().tag(key, value);
  }