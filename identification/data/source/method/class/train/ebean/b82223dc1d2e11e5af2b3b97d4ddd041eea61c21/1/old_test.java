  @Test
  public void getAsOfPredicate() throws Exception {

    String asOfPredicate = support.getAsOfPredicate("t0", "sys_period");
    assertEquals(asOfPredicate, "t0.sys_period @> ?::timestamptz");
  }