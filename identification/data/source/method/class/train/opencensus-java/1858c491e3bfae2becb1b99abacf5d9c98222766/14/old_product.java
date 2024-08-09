public final NonThrowingCloseable startScopedSpan() {
    return new ScopedSpanHandle(startSpan());
  }