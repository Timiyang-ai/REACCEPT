@Test
  public void updateAttribute() throws Exception {
    final String query = "S:update-attribute('a','b'), S:attribute('a')";
    assertEquals("b", get("?query=" + request(query)));
  }