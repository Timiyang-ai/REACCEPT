public NonThrowingCloseable startScopedSpan() {
    return new ScopedSpanHandle(start());
  }