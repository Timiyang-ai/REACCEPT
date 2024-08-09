static Scope withSpan(Span span) {
    return new WithSpan(span, ContextUtils.CONTEXT_SPAN_KEY);
  }