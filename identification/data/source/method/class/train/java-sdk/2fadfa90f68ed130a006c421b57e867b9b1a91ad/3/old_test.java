@Test
  public void testDelete() {
    final Request request = RequestBuilder.delete(urlWithQuery).build();
    assertEquals("DELETE", request.method());
    assertEquals(urlWithQuery, request.url().toString());
  }