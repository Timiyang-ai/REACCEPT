public void handleSend(@Nullable Resp response, @Nullable Throwable error, Span span) {
    if (span.isNoop()) return;
    try {
      parser.responseTags(adapter, response, error, span);
    } finally {
      span.finish();
    }
  }