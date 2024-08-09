@Test
  public void testCreateDirectory() throws QueryException {
    final String fun = check(FunDef.CREATEDIR);
    query(fun + "('" + PATH1 + "')");
    query(fun + "('" + PATH1 + "')");
    query(fun + "('" + PATH3 + "')");
    query("file:delete('" + PATH1 + "', true())");
    query("file:write('" + PATH1 + "', ())");
    error(fun + "('" + PATH1 + "')", Err.FILEEXISTS);
    error(fun + "('" + PATH3 + "')", Err.FILEEXISTS);
    query("file:delete('" + PATH1 + "')");
  }