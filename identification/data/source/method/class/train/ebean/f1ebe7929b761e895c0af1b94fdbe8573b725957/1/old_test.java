  @Test
  public void findNameStart() {
    assertEquals(5, BindParamsParser.findNameStart("some :name = ?", 0));
  }