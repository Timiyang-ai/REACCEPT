@Test
  public void testNode() throws BaseXException {
    final String fun = check(FunDef.NODEID, DBNode.class);
    query(fun + "(/html)", "1");
    query(fun + "(/ | /html)", "0 1");
  }