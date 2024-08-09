@Test
  public void testRun() throws BaseXException {
    // test wrong arguments
    args("util:run", String.class);

    // dynamically run query files
    query("util:run('etc/xml/input.xq')", "XML");
    error("util:run('etc/xml/xxx.xq')", "FODC0002");
  }