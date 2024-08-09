@Test
  public void testGet() throws QueryException {
    final String fun = check(Function.MAPGET);
    query(fun + "(map:new(), 1)", "");
    query(fun + "(map:entry(1,2), 1)", "2");
  }