@Test
  public void testEntries() throws BaseXException {
    final String fun = check(FunDef.ENTRIES, Uri.class);
    query(fun + "(xs:anyURI('" + ZIP + "'))");
  }