@Test
  public void testBuild() {
    final String xToken = X_TOKEN;
    final Request request =
        RequestBuilder.post(urlWithQuery)
          .bodyContent("body1", HttpMediaType.TEXT_PLAIN).header(X_TOKEN, "token1").build();

    assertEquals("POST", request.method());
    assertEquals("token1", request.header(xToken));
  }