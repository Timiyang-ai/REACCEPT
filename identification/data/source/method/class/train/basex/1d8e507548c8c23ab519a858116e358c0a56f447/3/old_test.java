@Test
  public void testInfo() throws BaseXException {
    // wrong arguments
    args("db:info");
    // standard test
    contains("db:info()", "ON");
    // run function on closed database
    new Close().execute(CTX);
    contains("db:open('db')/db:info()", "ON");
    error("db:info()", "BASX0002");
  }