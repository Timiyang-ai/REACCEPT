public final NonThrowingCloseable withSpan(Span span) {
    return CurrentSpanUtils.withSpan(checkNotNull(span, "span"));
  }