@Test
  public void testOpen() throws QueryException, BaseXException {
    final String fun = check(Function.OPEN);
    query("count(" + fun + "('db'))", "1");
    query("count(" + fun + "('db/'))", "1");

    // close database instance
    new Close().execute(CONTEXT);
    query("count(" + fun + "(<a>db</a>))", "1");
    query("count(" + fun + "('db/x'))", "0");
    query(fun + "('db')//title/text()", "XML");

    // run function on non-existing database
    new DropDB("db").execute(CONTEXT);
    error(fun + "('db')", Err.NODB);
  }