public Span handleReceive(HttpServerRequest request) {
    return defaultHandler.handleReceive(defaultExtractor, request);
  }