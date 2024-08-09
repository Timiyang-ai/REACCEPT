@Test
  public void testRename() throws QueryException, BaseXException {
    final String fun = check(Function.DBRENAME);

    new Add("etc/test/dir", "docs", "test").execute(CONTEXT);

    query(fun + "('db', 'test', 'newtest')", "");
    query("count(collection('db/newtest')) gt 0", "true");
  }