@Test
  public void testEntries() throws BaseXException {
    final String fun = check(FunDef.ENTRIES, String.class);
    query(fun + "('" + ZIP + "')");
  }