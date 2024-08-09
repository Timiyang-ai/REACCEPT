public Span handleSend(HttpClientRequest request) {
    HttpClientRequest.Adapter adapter = new HttpClientRequest.Adapter(request);
    return handleSend(adapter, nextClientSpan(adapter, request.unwrap()));
  }