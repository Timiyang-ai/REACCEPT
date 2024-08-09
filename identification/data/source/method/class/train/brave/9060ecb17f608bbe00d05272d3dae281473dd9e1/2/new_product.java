public Span handleReceive(HttpServerRequest request) {
    Span span = nextSpan(defaultExtractor.extract(request), request);
    return handleStart(new HttpServerRequest.ToHttpAdapter(request), request.unwrap(), span);
  }