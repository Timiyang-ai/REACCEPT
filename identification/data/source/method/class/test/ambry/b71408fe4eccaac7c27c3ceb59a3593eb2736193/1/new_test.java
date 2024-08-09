  @Test
  public void closeTest() {
    // request is keep-alive by default.
    HttpRequest request = createRequestWithHeaders(HttpMethod.GET, TestingUri.Close.toString());
    EmbeddedChannel channel = createEmbeddedChannel();
    channel.writeInbound(request);

    HttpResponse response = (HttpResponse) channel.readOutbound();
    assertEquals("Unexpected response status", HttpResponseStatus.INTERNAL_SERVER_ERROR, response.status());
    assertFalse("Inconsistent value for Connection header", HttpUtil.isKeepAlive(response));
    // drain the channel of content.
    while (channel.readOutbound() != null) {
    }
    assertFalse("Channel should be closed", channel.isOpen());
  }