@Test
  public void testWrite() throws QueryException {
    final String fun = check(FunDef.WRITE);

    error(fun + "('" + Prop.TMP + "', ())", Err.PATHISDIR);

    query(fun + "('" + PATH1 + "', '0')");
    query("file:size('" + PATH1 + "')", "1");
    query(fun + "('" + PATH1 + "', '0')");
    query("file:size('" + PATH1 + "')", "1");
    query(fun + "('" + PATH1 + "', '0', (), true())");
    query("file:size('" + PATH1 + "')", "2");
    query("file:delete('" + PATH1 + "')");
    query(fun + "('" + PATH1 + "', '0', (), true())");
    query("file:size('" + PATH1 + "')", "1");
    query("file:delete('" + PATH1 + "')");

    query(fun + "('" + PATH1 + "', 'a\u00e4'," +
      serialParams("<encoding>CP1252</encoding>") + ")");
    query("file:read('" + PATH1 + "', 'CP1252')", "a\u00e4");

    query(fun + "('" + PATH1 + "', '<a/>'," +
        serialParams("<method>text</method>") + ")");
    query("file:read('" + PATH1 + "')", "&amp;lt;a/&amp;gt;");
    query("file:delete('" + PATH1 + "')");
  }