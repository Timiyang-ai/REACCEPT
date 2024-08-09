@Test
  public void setRequestTest() {
    HttpRequest request = createRequestWithHeaders(HttpMethod.GET, TestingUri.SetRequestTest.toString());
    EmbeddedChannel channel = createEmbeddedChannel();
    channel.writeInbound(request);

    HttpResponse response = (HttpResponse) channel.readOutbound();
    assertEquals("Unexpected response status", HttpResponseStatus.ACCEPTED, response.getStatus());
  }