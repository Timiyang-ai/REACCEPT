@Test
  public void testList() throws QueryException {
    final String fun = check(FunDef.FLIST);
    error(fun + "('" + PATH1 + "')", Err.PATHNOTEXISTS);
    query("file:write('" + PATH1 + "', ())");
    error(fun + "('" + PATH1 + "')", Err.NOTDIR);
    contains(fun + "('" + Prop.TMP + "')", NAME);
    contains(fun + "('" + Prop.TMP + "',false())", NAME);
    contains(fun + "('" + Prop.TMP + "',false()," + "'FN')", NAME);
    contains(fun + "('" + Prop.TMP + "',false(),'" + NAME + "')", NAME);
    query(fun + "('" + Prop.TMP + "', false()," + "'XXX')", "");
    query("file:delete('" + PATH1 + "')");
  }