@Test
  public void testDelete() throws QueryException, BaseXException {
    final String fun = check(Function.DELETE);

    // add documents with certain prefix
    new Add("etc/test/dir", "docs", "test").execute(CONTEXT);
    query(fun + "('db', 'test')", "");
    query("count(collection('db/newtest')) eq 0", "true");
  }