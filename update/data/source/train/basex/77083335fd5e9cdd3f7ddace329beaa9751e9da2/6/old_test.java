@Test
  public void testList() throws BaseXException {
    // wrong arguments
    args("db:list");

    // create two other database and compare substring
    new CreateDB("daz").execute(CTX);
    new CreateDB("dba").execute(CTX);
    contains("db:list()", "daz db dba");
    new DropDB("daz").execute(CTX);
    new DropDB("dba").execute(CTX);
  }