public Span handleSend(HttpClientRequest request) {
    HttpClientRequest.Adapter adapter = new HttpClientRequest.Adapter(request);
    return handleSend(new HttpClientRequest.Adapter(request), nextSpan(adapter));
  }