static Scope withSpan(Span span, boolean endSpan) {
    return new ScopeInSpan(span, endSpan);
  }