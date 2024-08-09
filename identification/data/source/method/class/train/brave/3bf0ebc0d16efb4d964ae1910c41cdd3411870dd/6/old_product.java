public <Req> void request(HttpAdapter<Req, ?> adapter, Req req, SpanCustomizer customizer) {
    customizer.name(spanName(adapter, req));
    String path = adapter.path(req);
    if (path != null) customizer.tag("http.path", path);
  }