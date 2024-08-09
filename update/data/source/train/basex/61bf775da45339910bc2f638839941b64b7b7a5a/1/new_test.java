@Test
  public void entries() {
    final String fun = check(Function.ZIPENTRIES);
    query(fun + "('" + ZIP + "')");
  }