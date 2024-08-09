@Test
  public void testDelete() throws QueryException, BaseXException {
    final String fun = check(Function.DBDELETE);

    new Add(FLDR, "docs", "test").execute(CONTEXT);

    query(fun + "('db', 'test')", "");
    query("count(collection('db/test')) eq 0", "true");
  }