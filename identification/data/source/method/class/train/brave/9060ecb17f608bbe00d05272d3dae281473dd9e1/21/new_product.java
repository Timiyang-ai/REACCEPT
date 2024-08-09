public Span handleSend(HttpClientRequest request, Span span) {
    HttpClientRequest.Adapter adapter = new HttpClientRequest.Adapter(request);
    return handleSend(adapter, span);
  }