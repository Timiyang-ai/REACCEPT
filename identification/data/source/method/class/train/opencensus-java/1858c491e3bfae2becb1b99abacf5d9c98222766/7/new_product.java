@MustBeClosed
  public final Scope startScopedSpan() {
    return CurrentSpanUtils.withSpan(startSpan(), true);
  }