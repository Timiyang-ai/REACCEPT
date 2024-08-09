  @Test
  public void parse() {
    List<MediaType> result = MediaType.parse("application/json , text/html,*");
    assertEquals(3, result.size());
    assertEquals("application/json", result.get(0).getValue());
    assertEquals("text/html", result.get(1).getValue());
    assertEquals("*/*", result.get(2).getValue());

    assertEquals(0, MediaType.parse(null).size());
    assertEquals(0, MediaType.parse("").size());
    assertEquals(1, MediaType.parse("text/plain,").size());
  }