@Test
  public void testReplace() throws QueryException, BaseXException {
    final String fun = check(Function.DBREPLACE);

    new Add("etc/test/input.xml", null, "test").execute(CONTEXT);

    query(fun + "('db', 'test/input.xml', document { <root/> })");
    query("count(collection('db/test/input.xml')/html)", "0");
    query("count(collection('db/test/input.xml')/root)", "1");

    query(fun + "('db', 'test/input.xml', 'etc/test/input.xml')");
    query("count(collection('db/test/input.xml')/html)", "1");
    query("count(collection('db/test/input.xml')/root)", "0");
  }