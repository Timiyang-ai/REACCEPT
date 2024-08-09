public final Scope withSpan(Span span) {
    return CurrentSpanUtils.withSpan(checkNotNull(span, "span"));
  }