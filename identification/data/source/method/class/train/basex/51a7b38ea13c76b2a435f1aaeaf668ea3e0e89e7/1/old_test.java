@Test
  public void optimize() {
    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_OPTIMIZE.args(NAME));
    // opened database cannot be fully optimized
    error(_DB_OPTIMIZE.args(NAME, true), UPDBOPTERR_X);
    execute(new Close());
    query(_DB_OPTIMIZE.args(NAME, true));

    // specify additional index options
    final String[] nopt = { "maxcats", "maxlen", "indexsplitsize", "ftindexsplitsize" };
    for(final String k : nopt) {
      query(_DB_OPTIMIZE.args(NAME, false, " map { '" + k + "': 1 }"));
    }
    final String[] bopt = { "textindex", "attrindex", "ftindex", "stemming",
        "casesens", "diacritics" };
    for(final String k : bopt) {
      for(final boolean v : new boolean[] { true, false }) {
        query(_DB_OPTIMIZE.args(NAME, false, " map { '" + k + "':" + v + "() }"));
      }
    }
    final String[] sopt = { "language", "stopwords" };
    for(final String k : sopt) {
      query(_DB_OPTIMIZE.args(NAME, false, " map { '" + k + "':'' }"));
    }
    assertEquals(context.options.get(MainOptions.TEXTINDEX), true);

    error(_DB_OPTIMIZE.args(NAME, false, " map { 'xyz': 'abc' }"), BASX_OPTIONS_X);
    error(_DB_OPTIMIZE.args(NAME, false, " map { 'updindex': 1 }"), BASX_OPTIONS_X);
    error(_DB_OPTIMIZE.args(NAME, false, " map { 'maxlen': -1 }"), BASX_VALUE_X_X);
    error(_DB_OPTIMIZE.args(NAME, false, " map { 'maxlen': 'a' }"), BASX_VALUE_X_X);
    error(_DB_OPTIMIZE.args(NAME, false, " map { 'textindex':'nope' }"), BASX_VALUE_X_X);

    // check if optimize call preserves original options
    query(_DB_OPTIMIZE.args(NAME));
    final String[] indexes = { "textindex", "attrindex", "ftindex" };
    final String[] includes = { "textinclude", "attrinclude", "ftinclude" };
    for(final String i : indexes) query(_DB_INFO.args(NAME) + "//" + i + "/text()", "false");
    for(final String i : includes) query(_DB_INFO.args(NAME) + "//" + i + "/text()", "");

    execute(new Open(NAME));
    final CmdIndex[] cmds = { CmdIndex.TEXT, CmdIndex.ATTRIBUTE, CmdIndex.FULLTEXT };
    for(final String i : includes) execute(new Set(i, "a"));
    for(final CmdIndex cmd : cmds) execute(new CreateIndex(cmd));
    execute(new Close());

    query(_DB_OPTIMIZE.args(NAME));
    for(final String i : indexes) query(_DB_INFO.args(NAME) + "//" + i + "/text()", "true");
    for(final String i : includes) query(_DB_INFO.args(NAME) + "//" + i + "/text()", "a");

    execute(new Open(NAME));
    for(final String i : includes) execute(new Set(i, ""));
    for(final CmdIndex cmd : cmds) execute(new DropIndex(cmd));
    execute(new Close());

    query(_DB_OPTIMIZE.args(NAME));
    for(final String i : indexes) query(_DB_INFO.args(NAME) + "//" + i + "/text()", "false");
    for(final String i : includes) query(_DB_INFO.args(NAME) + "//" + i + "/text()", "");

    query(_DB_OPTIMIZE.args(NAME, true, " map {"
        + "'textindex':true(),'attrindex':true(),'ftindex':true(),'updindex':true(),"
        + "'textinclude':'a','attrinclude':'a','ftinclude':'a'"
        + " }"));
    for(final String i : indexes) query(_DB_INFO.args(NAME) + "//" + i + "/text()", "true");
    for(final String i : includes) query(_DB_INFO.args(NAME) + "//" + i + "/text()", "a");
    query(_DB_INFO.args(NAME) + "//updindex/text()", "true");
  }