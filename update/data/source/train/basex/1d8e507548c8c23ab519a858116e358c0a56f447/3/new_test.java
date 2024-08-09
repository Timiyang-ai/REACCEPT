@Test
  public void testInfo() throws BaseXException {
    // wrong arguments
    final String fun = check(FunDef.INFO, String.class);

    // standard test
    contains(fun + "()", "ON");

    // drop indexes and check index queries
    final String[] types = { "text", "attribute", "fulltext" };
    for(final String type : types) new DropIndex(type).execute(CTX);
    for(final String type : types) query(fun + "('" + type + "')");
    // create indexes and check index queries
    for(final String type : types) new CreateIndex(type).execute(CTX);
    for(final String type : types) query(fun + "('" + type + "')");
    // check name indexes
    query(fun + "('tag')");
    query(fun + "('attname')");

    // run function on closed database
    new Close().execute(CTX);
    contains("db:open('db')/" + fun + "()", "ON");
    contains("db:open('db')/" + fun + "('tag')", ":");
    error(fun + "('tag')", Err.NODBCTX);
  }