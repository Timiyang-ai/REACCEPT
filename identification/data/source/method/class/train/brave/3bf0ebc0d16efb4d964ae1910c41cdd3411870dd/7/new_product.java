public <Req> void request(HttpAdapter<Req, ?> adapter, Req req, SpanCustomizer customizer) {
    String name = spanName(adapter, req);
    if (name != null) customizer.name(name);
    String method = adapter.method(req);
    if (method != null) customizer.tag("http.method", method);
    String path = adapter.path(req);
    if (path != null) customizer.tag("http.path", path);
  }