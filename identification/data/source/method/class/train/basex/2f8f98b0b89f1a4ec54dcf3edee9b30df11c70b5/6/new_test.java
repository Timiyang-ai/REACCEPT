@Test
  public void testOptimize() throws QueryException {
    final String fun = check(Function.DBOPTIMIZE);

    query(fun + "('db')");
    query(fun + "('db', true())");
  }