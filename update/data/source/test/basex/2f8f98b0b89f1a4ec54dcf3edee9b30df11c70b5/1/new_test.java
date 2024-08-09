@Test
  public void testOpenPre() throws QueryException {
    final String fun = check(Function.DBOPENPRE);
    query(fun + "('db', 0)//title/text()", "XML");
    error(fun + "('db', -1)", Err.IDINVALID);
  }