@Test
  public void testGET() throws Exception {
    assertEquals("123", get("query=1+to+3&wrap=no"));
  }