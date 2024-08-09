  @Test public void response_tagsStatusAndErrorOnResponseCode() {
    when(adapter.statusCodeAsInt(response)).thenReturn(400);

    parser.response(adapter, response, null, customizer);

    verify(customizer).tag("http.status_code", "400");
    verify(customizer).tag("error", "400");
  }