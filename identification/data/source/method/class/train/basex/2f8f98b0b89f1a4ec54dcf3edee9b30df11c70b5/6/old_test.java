@Test
  public void testOptimize() throws QueryException {
    final String fun = check(Function.OPTIMIZE);

    query(fun + "('db')");
    query(fun + "('db', true())");
  }