  @Test public void request_addsMethodAndPath() {
    when(adapter.method(request)).thenReturn("GET");
    when(adapter.path(request)).thenReturn("/foo");

    parser.request(adapter, request, customizer);

    verify(customizer).tag("http.method", "GET");
    verify(customizer).tag("http.path", "/foo");
  }