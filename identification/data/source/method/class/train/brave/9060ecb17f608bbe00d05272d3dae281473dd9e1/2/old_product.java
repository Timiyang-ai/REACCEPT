public Span handleReceive(HttpServerRequest request) {
    HttpServerRequest.Adapter adapter = new HttpServerRequest.Adapter(request);
    Span span = nextSpan(adapter, defaultExtractor.extract(request), adapter.unwrapped);
    return handleStart(adapter, adapter.unwrapped, span);
  }