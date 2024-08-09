public Span handleSend(HttpClientRequest request, Span span) {
    if (request == null) throw new NullPointerException("request == null");
    if (span == null) throw new NullPointerException("span == null");
    defaultInjector.inject(span.context(), request);
    return handleStart(new HttpClientRequest.ToHttpAdapter(request), request.unwrap(), span);
  }