@Test
  public void testIndex() throws BaseXException {
    final String fun = check(FunDef.FULLTEXT, String.class);

    // run function without and with index
    new DropIndex("fulltext").execute(CTX);
    error(fun + "('assignments')", Err.NOIDX);
    new CreateIndex("fulltext").execute(CTX);
    query(fun + "('assignments')", "Assignments");
    query(fun + "('XXX')", "");

    // run function on closed database
    new Close().execute(CTX);
    query("db:open('db')/" + fun + "('assignments')", "Assignments");
    error(fun + "('XXX')", Err.NODBCTX);
  }