static Span getCurrentSpan() {
    return ContextUtils.CONTEXT_SPAN_KEY.get();
  }