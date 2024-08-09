@Test
  public void get() throws Exception {
    assertEquals("", get("?query=" + request("S:get('a')")));
  }