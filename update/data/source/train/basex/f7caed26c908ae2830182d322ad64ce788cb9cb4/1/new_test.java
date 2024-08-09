@Test
  public void testNode() throws BaseXException {
    // wrong arguments
    args("db:node-id", (Class<?>) null);
    args("db:node-pre", (Class<?>) null);
    error("db:node-id(1)", "BASX0002");
    error("db:node-pre(1)", "BASX0002");

    // test results
    query("db:node-id(/html)", "1");
    query("db:node-pre(/html)", "1");
    query("db:node-pre(/ | /html)", "0 1");
  }