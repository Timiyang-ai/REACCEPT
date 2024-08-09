@Test
  public void testDelete() {
    final Request request = RequestBuilder.delete(HttpUrl.parse(urlWithQuery)).build();
    assertEquals("DELETE", request.method());
    assertEquals(urlWithQuery, request.url().toString());
  }