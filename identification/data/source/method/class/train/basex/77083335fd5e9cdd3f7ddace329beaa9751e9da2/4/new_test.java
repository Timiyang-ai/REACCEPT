@Test
  public void testList() throws BaseXException {
    final String fun = check(FunDef.LIST);

    // create two other database and compare substring
    new CreateDB("daz").execute(CTX);
    new CreateDB("dba").execute(CTX);
    contains(fun + "()", "daz db dba");
    new DropDB("daz").execute(CTX);
    new DropDB("dba").execute(CTX);
  }