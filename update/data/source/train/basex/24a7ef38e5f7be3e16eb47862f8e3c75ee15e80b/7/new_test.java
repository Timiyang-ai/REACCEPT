@Test
  public void testDelete() throws QueryException {
    final String fun = check(FunDef.DELETE);
    query("file:create-directory('" + PATH3 + "')");
    query(fun + "('" + PATH3 + "')");
    query("file:create-directory('" + PATH3 + "')");
    query("file:write('" + PATH4 + "', ())");
    query(fun + "('" + PATH1 + "')");
    error(fun + "('" + PATH1 + "')", Err.PATHNOTEXISTS);
  }