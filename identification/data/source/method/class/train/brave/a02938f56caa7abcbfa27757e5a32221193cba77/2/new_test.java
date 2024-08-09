  @Test public void route() {
    when(event.getContainerRequest()).thenReturn(request);
    when(request.getProperty("http.route")).thenReturn("/items/{itemId}");

    assertThat(new HttpServerResponse(event).route())
      .isEqualTo("/items/{itemId}");
  }