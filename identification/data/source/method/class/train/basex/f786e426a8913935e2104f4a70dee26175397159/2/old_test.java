@Test
  public void testAdd() throws QueryException {
    final String fun = check(Function.ADD);

    query(fun + "('db', document { <root/> }, 'test1')");
    query("count(collection('db/test1')) eq 1", "true");
  }