@Test
  public void attribute() throws Exception {
    assertEquals("", get("?query=" + request("S:attribute('a')")));
  }