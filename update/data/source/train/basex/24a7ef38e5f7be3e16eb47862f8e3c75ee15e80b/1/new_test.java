@Test
  public void testWriteBinary() throws QueryException {
    final String fun = check(FunDef.WRITEBIN);

    final String a = "xs:base64Binary('MA==')";
    error(fun + "('" + Prop.TMP + "', " + a + ")", Err.PATHISDIR);
    query(fun + "('" + PATH1 + "', " + a + ")");
    query("file:size('" + PATH1 + "')", "1");
    query(fun + "('" + PATH1 + "', " + a + ")");
    query("file:size('" + PATH1 + "')", "1");
    query("file:delete('" + PATH1 + "')");
  }