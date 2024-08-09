@Test
  public void testAttribute() throws QueryException, BaseXException {
    final String fun = check(Function.ATTR);

    // run function without and with index
    new DropIndex("attribute").execute(CONTEXT);
    query("data(" + fun + "('db', '0'))", "0");
    new CreateIndex("attribute").execute(CONTEXT);
    query("data(" + fun + "('db', '0'))", "0");
    query("data(" + fun + "('db', '0', 'id'))", "0");
    query("data(" + fun + "('db', '0', 'XXX'))", "");
    query(fun + "('db', 'XXX')", "");
  }