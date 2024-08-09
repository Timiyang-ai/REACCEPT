@Test
  public void testIndex() throws BaseXException {
    final String[] types = { "text", "attribute", "fulltext" };
    // drop indexes and check index queries
    for(final String type : types) new DropIndex(type).execute(CTX);
    for(final String type : types) {
      error("db:" + type +  "-index('xml')", "BASX0001");
    }
    // create indexes and check index queries
    for(final String type : types) new CreateIndex(type).execute(CTX);
    for(final String type : types) query("db:" + type +  "-index('xml')");

    // test wrong arguments
    args("db:text-index", (Class<?>) null);
    args("db:fulltext-index", String.class);

    // check index results
    query("db:text-index('XML')", "XML");
    query("db:text-index('XML')", "XML");
    query("db:text-index('XXX')", "");
    query("data(" + "db:attribute-index('0'))", "0");
    query("data(" + "db:attribute-index('0', 'id'))", "0");
    query("data(" + "db:attribute-index('0', 'X'))", "");
    query("db:fulltext-index('assignments')", "Assignments");
    query("db:fulltext-index('XXX')", "");

    // run function on closed database
    new Close().execute(CTX);
    query("db:open('db')/db:text-index('XML')", "XML");
    error("db:text-index('x')", "BASX0002");
  }