@Test
  public void testPost() {
    final Request request = RequestBuilder.post(HttpUrl.parse(url)).build();
    assertEquals("POST", request.method());
    assertEquals(url, request.url().toString());
  }