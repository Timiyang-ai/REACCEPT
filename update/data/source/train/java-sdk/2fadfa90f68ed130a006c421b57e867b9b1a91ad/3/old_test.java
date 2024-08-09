@Test
  public void testGet() {
    final Request request = RequestBuilder.get(urlWithQuery).build();
    assertEquals("GET", request.method());
    assertEquals(urlWithQuery, request.url().toString());
  }