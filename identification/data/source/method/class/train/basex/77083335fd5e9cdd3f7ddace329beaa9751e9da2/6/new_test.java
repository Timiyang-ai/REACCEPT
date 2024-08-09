@Test
  public void testMB() throws BaseXException {
    final String fun = check(FunDef.MB, (Class<?>) null, Boolean.class);
    query(fun + "(())");
    query(fun + "(1 to 1000, false())");
    query(fun + "(1 to 1000, true())");
  }