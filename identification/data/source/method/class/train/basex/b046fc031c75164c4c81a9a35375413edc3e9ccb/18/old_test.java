@Test
  public void optimize() throws BaseXException {
    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_OPTIMIZE.args(NAME));
    error(_DB_OPTIMIZE.args(NAME, true), Err.UPDBOPTERR);
    new Close().execute(context);
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
        query(_DB_OPTIMIZE.args(NAME, false, " map { '" + k + "':=" + v + "() }"));
      }
    }
    final String[] sopt = { "language", "stopwords" };
    for(final String k : sopt) {
      query(_DB_OPTIMIZE.args(NAME, false, " map { '" + k + "':='' }"));
    }
    assertEquals(context.options.get(MainOptions.TEXTINDEX), true);

    error(_DB_OPTIMIZE.args(NAME, false, " map { 'xyz': 'abc' }"), Err.BASX_OPTIONS);
    error(_DB_OPTIMIZE.args(NAME, false, " map { 'updindex': 1 }"), Err.BASX_OPTIONS);
    error(_DB_OPTIMIZE.args(NAME, false, " map { 'maxlen': -1 }"), Err.BASX_VALUE);
    error(_DB_OPTIMIZE.args(NAME, false, " map { 'maxlen': 'a' }"), Err.BASX_VALUE);
    error(_DB_OPTIMIZE.args(NAME, false, " map { 'textindex':'nope' }"), Err.BASX_VALUE);

    // check if optimize call preserves original options
    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_INFO.args(NAME) + "//textindex/text()", "false");
    query(_DB_INFO.args(NAME) + "//attrindex/text()", "false");
    query(_DB_INFO.args(NAME) + "//ftindex/text()", "false");

    new Open(NAME).execute(context);
    new CreateIndex(Commands.CmdIndex.TEXT).execute(context);
    new CreateIndex(Commands.CmdIndex.ATTRIBUTE).execute(context);
    new CreateIndex(Commands.CmdIndex.FULLTEXT).execute(context);
    new Close().execute(context);

    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_INFO.args(NAME) + "//textindex/text()", "true");
    query(_DB_INFO.args(NAME) + "//attrindex/text()", "true");
    query(_DB_INFO.args(NAME) + "//ftindex/text()", "true");

    new Open(NAME).execute(context);
    new DropIndex(Commands.CmdIndex.TEXT).execute(context);
    new DropIndex(Commands.CmdIndex.ATTRIBUTE).execute(context);
    new DropIndex(Commands.CmdIndex.FULLTEXT).execute(context);
    new Close().execute(context);

    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_INFO.args(NAME) + "//textindex/text()", "false");
    query(_DB_INFO.args(NAME) + "//attrindex/text()", "false");
    query(_DB_INFO.args(NAME) + "//ftindex/text()", "false");

    query(_DB_OPTIMIZE.args(NAME, true,
        " map { 'textindex':=true(),'attrindex':=true(),'ftindex':=true(),'updindex':=true() }"));
    query(_DB_INFO.args(NAME) + "//textindex/text()", "true");
    query(_DB_INFO.args(NAME) + "//attrindex/text()", "true");
    query(_DB_INFO.args(NAME) + "//ftindex/text()", "true");
    query(_DB_INFO.args(NAME) + "//updindex/text()", "true");
  }