public Span handleSend(HttpClientRequest request) {
    if (request == null) throw new NullPointerException("request == null");
    return handleSend(request, tracer.nextSpan(httpSampler, request));
  }