@Test
  public void setRequestTest() {
    HttpRequest request = createRequestWithHeaders(HttpMethod.GET, TestingUri.SetRequest.toString());
    HttpHeaders.setKeepAlive(request, false);
    EmbeddedChannel channel = createEmbeddedChannel();
    channel.writeInbound(request);

    HttpResponse response = (HttpResponse) channel.readOutbound();
    assertEquals("Unexpected response status", HttpResponseStatus.ACCEPTED, response.getStatus());
    assertFalse("Channel not closed on the server", channel.isActive());
  }