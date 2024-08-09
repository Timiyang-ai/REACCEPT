@Override public SpanCustomizer name(String name) {
    return tracer.currentSpanCustomizer().name(name);
  }