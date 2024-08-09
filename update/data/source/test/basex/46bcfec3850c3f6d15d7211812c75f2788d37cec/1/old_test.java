@Test
  public void testDelete() throws QueryException, BaseXException {
    final String fun = check(Function.DBDELETE);

    new Add("etc/test/dir", "docs", "test").execute(CONTEXT);

    query(fun + "('db', 'test')", "");
    query("count(collection('db/test')) eq 0", "true");
  }