@Test
  public void testPut() {
    final Request request = RequestBuilder.put(urlWithQuery).build();
    assertEquals("PUT", request.method());
    assertEquals(urlWithQuery, request.url().toString());
  }