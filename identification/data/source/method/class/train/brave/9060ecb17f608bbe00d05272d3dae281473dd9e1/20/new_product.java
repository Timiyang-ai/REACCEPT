public void handleReceive(@Nullable Resp response, @Nullable Throwable error, Span span) {
    if (span.isNoop()) return;

    try {
      if (response != null || error != null) {
        String message = adapter.parseError(response, error);
        if (message != null) span.tag(zipkin.Constants.ERROR, message);
      }
      if (response != null) parser.responseTags(adapter, response, span);
    } finally {
      span.finish();
    }
  }