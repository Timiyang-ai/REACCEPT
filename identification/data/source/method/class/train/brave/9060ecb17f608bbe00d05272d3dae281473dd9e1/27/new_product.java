public void handleSend(@Nullable Resp response, @Nullable Throwable error, Span span) {
    if (span.isNoop()) return;

    // Ensure user-code can read the current trace context
    Tracer.SpanInScope ws = tracer.withSpanInScope(span);
    try {
      parser.response(adapter, response, error, span.customizer());
    } finally {
      ws.close();
      span.finish();
    }
  }