@Test
  public void testBuild() {
    final String xToken = X_TOKEN;
    final RequestBuilder builder =  RequestBuilder.post(urlWithQuery)
      .bodyContent("body1", HttpMediaType.TEXT_PLAIN).header(X_TOKEN, "token1");
    final Request request = builder.build();

    assertEquals("POST", request.method());
    assertEquals("token1", request.header(xToken));
    assertNotNull(builder.toString());
  }