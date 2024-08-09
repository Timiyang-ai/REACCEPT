@Test
  public void testRead() throws QueryException {
    final String fun = check(FunDef.READ);
    error(fun + "('" + PATH1 + "')", Err.PATHNOTEXISTS);
    error(fun + "('" + Prop.TMP + "')", Err.PATHISDIR);
    query("file:write('" + PATH1 + "', 'a\u00e4')");
    query(fun + "('" + PATH1 + "')", "a\u00e4");
    error(fun + "('" + PATH1 + "', 'UNKNOWN')", Err.ENCNOTEXISTS);
    assertTrue(query(fun + "('" + PATH1 + "', 'CP1252')").length() == 3);
    query("file:delete('" + PATH1 + "')");
  }