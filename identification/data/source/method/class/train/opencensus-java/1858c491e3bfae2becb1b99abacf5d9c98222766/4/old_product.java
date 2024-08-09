public NonThrowingCloseable withSpan(Span span) {
    return ContextUtils.withSpan(checkNotNull(span, "span"));
  }