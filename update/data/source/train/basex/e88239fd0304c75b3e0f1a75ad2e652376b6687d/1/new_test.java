@Test
  public void optimize() throws BaseXException {
    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_OPTIMIZE.args(NAME));
    error(_DB_OPTIMIZE.args(NAME, "true()"), Err.UPDBOPTERR);
    new Close().execute(context);
    query(_DB_OPTIMIZE.args(NAME, "true()"));

    // specify additional index options
    final String[] nopt = { "maxcats", "maxlen", "indexsplitsize", "ftindexsplitsize" };
    for(final String k : nopt) {
      query(_DB_OPTIMIZE.args(NAME, "false()", " map { '" + k + "':=1 }"));
    }
    final String[] bopt = { "textindex", "attrindex", "ftindex", "stemming",
        "casesens", "diacritics" };
    for(final String k : bopt) {
      for(final boolean v : new boolean[] { true, false }) {
        query(_DB_OPTIMIZE.args(NAME, "false()", " map { '" + k + "':=" + v + "() }"));
      }
    }
    final String[] sopt = { "language", "stopwords" };
    for(final String k : sopt) {
      query(_DB_OPTIMIZE.args(NAME, "false()", " map { '" + k + "':='' }"));
    }
    assertEquals(context.prop.is(Prop.TEXTINDEX), true);

    error(_DB_OPTIMIZE.args(NAME, "false()", " map { 'updindex':=1 }"), Err.BASX_OPTIONS);
    error(_DB_OPTIMIZE.args(NAME, "false()", " map { 'xyz':='abc' }"), Err.BASX_OPTIONS);
    error(_DB_OPTIMIZE.args(NAME, "false()", " map { 'maxlen':=-1 }"), Err.BASX_VALUE);
    error(_DB_OPTIMIZE.args(NAME, "false()", " map { 'maxlen':='a' }"), Err.BASX_VALUE);
  }