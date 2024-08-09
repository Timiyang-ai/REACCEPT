public void handleReceive(@Nullable Resp response, @Nullable Throwable error, Span span) {
    if (span.isNoop()) return;
    Tracer.SpanInScope ws = tracer.withSpanInScope(span);
    try {
      parser.response(adapter, response, error, span);
    } finally {
      ws.close();
      span.finish();
    }
  }