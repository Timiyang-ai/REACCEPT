  @DisplayName("creates a new handler")
  @Test
  void newHandler() {
    assertThat(WebsocketRouteTransport.newHandler(duplexConnection -> null)).isNotNull();
  }