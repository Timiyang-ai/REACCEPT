public Resp handleReceive(Resp response, Span span) {
    if (span.isNoop()) return response;

    try {
      parser.responseTags(adapter, response, span);
    } finally {
      span.finish();
    }
    return response;
  }