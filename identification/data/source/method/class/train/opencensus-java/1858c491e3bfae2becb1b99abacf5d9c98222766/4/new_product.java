public final NonThrowingCloseable withSpan(Span span) {
    return ContextUtils.withSpan(checkNotNull(span, "span"));
  }