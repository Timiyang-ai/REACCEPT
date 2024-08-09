@Test
  public void entries() throws Exception {
    final String fun = check(Function.ZIPENTRIES);
    query(fun + "('" + ZIP + "')");
  }