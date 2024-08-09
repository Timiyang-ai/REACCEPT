@Test
  public void closeTest() {
    // request is keep-alive by default.
    HttpRequest request = createRequestWithHeaders(HttpMethod.GET, TestingUri.Close.toString());
    EmbeddedChannel channel = createEmbeddedChannel();
    channel.writeInbound(request);

    // drain the channel of content.
    while (channel.readOutbound() != null) {
    }
    assertFalse("Channel should be closed", channel.isOpen());
  }