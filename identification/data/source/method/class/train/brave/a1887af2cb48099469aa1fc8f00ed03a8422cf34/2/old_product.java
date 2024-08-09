public SpanInScope withSpanInScope(Span span) {
    return new SpanInScope(currentTraceContext.newScope(span.context()));
  }