@Test
  public void testRun() throws BaseXException {
    final String fun = check(FunDef.RUN, String.class);
    query(fun + "('etc/xml/input.xq')", "XML");
    error(fun + "('etc/xml/xxx.xq')", Err.UNDOC);
  }