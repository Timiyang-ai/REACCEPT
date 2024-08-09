  @Test
  public void parse_text() throws Exception {

    DbPlatformType type = DbPlatformTypeParser.parse("text");

    assertEquals(type.getName(), "text");
    assertEquals(type.getDefaultLength(), 0);
    assertEquals(type.getDefaultScale(), 0);

    assertEquals(type.renderType(0, 0), "text");
    assertEquals(type.renderType(40, 0), "text");
  }