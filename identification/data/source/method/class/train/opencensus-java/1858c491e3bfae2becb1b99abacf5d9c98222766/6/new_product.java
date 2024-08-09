@MustBeClosed
  public final Scope withSpan(Span span) {
    return CurrentSpanUtils.withSpan(Utils.checkNotNull(span, "span"), /* endSpan= */ false);
  }