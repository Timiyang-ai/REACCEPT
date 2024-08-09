@Test
  public void set() throws Exception {
    final String query = "S:set('a','b'), S:get('a')";
    assertEquals("b", get("?query=" + request(query)));
  }