@Test
  public void testInfo() throws QueryException, BaseXException {
    // wrong arguments
    final String fun = check(Function.DBINFO);

    // standard test
    contains(fun + "('db')", INFOON);

    // drop indexes and check index queries
    final String[] types = { "text", "attribute", "fulltext" };
    for(final String type : types) new DropIndex(type).execute(CONTEXT);
    for(final String type : types) query(fun + "('db', '" + type + "')");
    // create indexes and check index queries
    for(final String type : types) new CreateIndex(type).execute(CONTEXT);
    for(final String type : types) query(fun + "('db', '" + type + "')");
    // check name indexes
    query(fun + "('db', 'tag')");
    query(fun + "('db', 'attname')");
  }