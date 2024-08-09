@Test
  public void testEntries() throws Exception {
    final String fun = check(FunDef.ENTRIES, String.class);
    query(fun + "('" + ZIP + "')");
  }