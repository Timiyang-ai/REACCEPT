public final Span getCurrentSpan() {
    Span currentSpan = CurrentSpanUtils.getCurrentSpan();
    return currentSpan != null ? currentSpan : BlankSpan.INSTANCE;
  }