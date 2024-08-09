public Span handleSend(HttpClientRequest request, Span span) {
    return defaultHandler.handleSend(defaultInjector, request, request, span);
  }