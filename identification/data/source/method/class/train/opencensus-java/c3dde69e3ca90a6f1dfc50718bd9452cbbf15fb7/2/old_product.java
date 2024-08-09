public Span getCurrentSpan() {
    Span currentSpan = contextSpanHandler.getCurrentSpan();
    return currentSpan != null ? currentSpan : BlankSpan.getInstance();
  }