@Test
  public void testReplace() throws QueryException, BaseXException {
    final String fun = check(Function.REPLACEDOC);

    new Add("etc/test/input.xml", null, "test").execute(CONTEXT);
    query(fun + "('db', 'test/input.xml', document { <root/> })");
    query("count(collection('db/test/input.xml')/html) eq 0", "true");
    query("count(collection('db/test/input.xml')/root) eq 1", "true");
  }