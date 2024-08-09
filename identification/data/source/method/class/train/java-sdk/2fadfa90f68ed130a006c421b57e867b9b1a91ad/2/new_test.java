@Test
  public void testGet() {
    final Request request = RequestBuilder.get(HttpUrl.parse(urlWithQuery)).build();
    assertEquals("GET", request.method());
    assertEquals(urlWithQuery, request.url().toString());
  }