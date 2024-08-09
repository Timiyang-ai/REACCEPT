@Test
  public void testCopy() throws QueryException {
    final String fun = check(FunDef.COPY);

    query("file:write('" + PATH1 + "', 'a')");
    query(fun + "('" + PATH1 + "', '" + PATH2 + "')");
    query(fun + "('" + PATH1 + "', '" + PATH2 + "')");
    query(fun + "('" + PATH2 + "', '" + PATH2 + "')");
    query("file:size('" + PATH1 + "')", "1");
    query("file:size('" + PATH2 + "')", "1");
    error(fun + "('" + PATH1 + "', '" + PATH3 + "')", Err.NOTDIR);

    query("file:delete('" + PATH1 + "')");
    query("file:delete('" + PATH2 + "')");
  }