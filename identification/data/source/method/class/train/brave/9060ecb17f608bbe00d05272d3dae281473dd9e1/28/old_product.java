public Span handleSend(HttpClientRequest request) {
    return handleSend(request, defaultHandler.nextSpan(request));
  }