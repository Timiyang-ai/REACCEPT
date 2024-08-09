public final Scope startScopedSpan() {
    return new ScopedSpanHandle(startSpan());
  }