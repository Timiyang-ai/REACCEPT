@Test
  public void testText() throws QueryException, BaseXException {
    final String fun = check(Function.TEXT);

    // run function without and with index
    new DropIndex("text").execute(CONTEXT);
    query(fun + "('db', 'XML')", "XML");
    new CreateIndex("text").execute(CONTEXT);
    query(fun + "('db', 'XML')", "XML");
    query(fun + "('db', 'XXX')", "");
  }