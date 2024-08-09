@Test
  public void testPost() {
    final Request request = RequestBuilder.post(url).build();
    assertEquals("POST", request.method());
    assertEquals(url, request.url().toString());
  }