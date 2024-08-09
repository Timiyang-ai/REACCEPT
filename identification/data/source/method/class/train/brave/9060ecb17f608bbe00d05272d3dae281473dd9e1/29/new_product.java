public void handleSend(@Nullable Resp response, @Nullable Throwable error, Span span) {
    if (span.isNoop()) return;

    try {
      if (error != null) {
        String message = error.getMessage();
        if (message == null) message = error.getClass().getSimpleName();
        span.tag(zipkin.Constants.ERROR, message);
      }
      if (response != null) parser.responseTags(adapter, response, span);
    } finally {
      span.finish();
    }
  }