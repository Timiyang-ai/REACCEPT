public NonThrowingCloseable withSpan(Span span) {
    return contextSpanHandler.withSpan(checkNotNull(span, "span"));
  }