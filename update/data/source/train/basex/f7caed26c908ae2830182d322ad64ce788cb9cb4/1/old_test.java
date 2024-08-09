@Test
  public void testNode() throws BaseXException {
    // wrong arguments
    args("db:node-id", DBNode.class);
    args("db:node-pre", DBNode.class);

    // test results
    query("db:node-id(/html)", "1");
    query("db:node-pre(/html)", "1");
    query("db:node-pre(/ | /html)", "0 1");
  }