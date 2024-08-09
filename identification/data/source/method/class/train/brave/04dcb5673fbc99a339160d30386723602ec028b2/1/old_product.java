@Override public SpanCustomizer annotate(long timestamp, String value) {
    recorder().annotate(context(), timestamp, value);
    return this;
  }