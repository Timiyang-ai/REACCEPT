@Test
  public void testMove() throws QueryException {
    final String fun = check(FunDef.MOVE);

    error(fun + "('" + PATH1 + "', '" + PATH2 + "')", Err.PATHNOTEXISTS);
    query("file:write('" + PATH1 + "', 'a')");
    query(fun + "('" + PATH1 + "', '" + PATH2 + "')");
    query(fun + "('" + PATH2 + "', '" + PATH1 + "')");
    query(fun + "('" + PATH1 + "', '" + PATH1 + "')");
    error(fun + "('" + PATH1 + "', '" + PATH4 + "')", Err.NOTDIR);
    query("file:size('" + PATH1 + "')", "1");
    query("file:exists('" + PATH2 + "')", "false");
    query("file:delete('" + PATH1 + "')");
  }