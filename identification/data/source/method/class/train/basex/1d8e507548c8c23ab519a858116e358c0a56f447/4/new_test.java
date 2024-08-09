@Test
  public void testSystem() throws BaseXException {
    // wrong arguments
    final String fun = check(FunDef.SYSTEM);
    contains(fun + "()", "ON");
  }