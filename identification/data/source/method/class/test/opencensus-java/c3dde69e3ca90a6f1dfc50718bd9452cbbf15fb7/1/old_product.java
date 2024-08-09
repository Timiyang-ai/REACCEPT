public Span getCurrentSpan() {
    Span currentSpan = ContextUtils.getCurrentSpan();
    return currentSpan != null ? currentSpan : BlankSpan.INSTANCE;
  }