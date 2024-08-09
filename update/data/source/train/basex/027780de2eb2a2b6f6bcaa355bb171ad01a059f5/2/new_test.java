@Test
  public void testAdd() throws QueryException {
    final String fun = check(Function.DBADD);

    query(fun + "('db', document { <root/> }, 'test1.xml')");
    query("count(collection('db/test1.xml')/root)", "1");

    query(fun + "('db', document { <root/> }, 'test2.xml', 'test')");
    query("count(collection('db/test/test2.xml')/root)", "1");

    query(fun + "('db', 'etc/test/input.xml', '', 'test')");
    query("count(collection('db/test/input.xml')/html)", "1");

    query(fun + "('db', 'etc/test/input.xml', 'test3.xml', 'test')");
    query("count(collection('db/test/test3.xml')/html)", "1");

    query(fun + "('db', '" + FLDR + "', '', 'test/dir')");
    query("count(collection('db/test/dir'))", NFLDR);
  }