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
    assertEquals(context.options.is(Options.TEXTINDEX), true);

    error(_DB_OPTIMIZE.args(NAME, "false()", " map { 'updindex':=1 }"), Err.BASX_OPTIONS);
    error(_DB_OPTIMIZE.args(NAME, "false()", " map { 'xyz':='abc' }"), Err.BASX_OPTIONS);
    error(_DB_OPTIMIZE.args(NAME, "false()", " map { 'maxlen':=-1 }"), Err.BASX_VALUE);
    error(_DB_OPTIMIZE.args(NAME, "false()", " map { 'maxlen':='a' }"), Err.BASX_VALUE);

    // check if optimize call preserves original options
    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_INFO.args(NAME) + "//textindex/text()", "OFF");
    query(_DB_INFO.args(NAME) + "//attributeindex/text()", "OFF");
    query(_DB_INFO.args(NAME) + "//fulltextindex/text()", "OFF");

    new Open(NAME).execute(context);
    new CreateIndex(Commands.CmdIndex.TEXT).execute(context);
    new CreateIndex(Commands.CmdIndex.ATTRIBUTE).execute(context);
    new CreateIndex(Commands.CmdIndex.FULLTEXT).execute(context);
    new Close().execute(context);

    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_INFO.args(NAME) + "//textindex/text()", "ON");
    query(_DB_INFO.args(NAME) + "//attributeindex/text()", "ON");
    query(_DB_INFO.args(NAME) + "//fulltextindex/text()", "ON");

    new Open(NAME).execute(context);
    new DropIndex(Commands.CmdIndex.TEXT).execute(context);
    new DropIndex(Commands.CmdIndex.ATTRIBUTE).execute(context);
    new DropIndex(Commands.CmdIndex.FULLTEXT).execute(context);
    new Close().execute(context);

    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_INFO.args(NAME) + "//textindex/text()", "OFF");
    query(_DB_INFO.args(NAME) + "//attributeindex/text()", "OFF");
    query(_DB_INFO.args(NAME) + "//fulltextindex/text()", "OFF");

    query(_DB_OPTIMIZE.args(NAME, "true()",
        " map { 'textindex':=true(),'attrindex':=true(),'ftindex':=true() }"));
    query(_DB_INFO.args(NAME) + "//textindex/text()", "ON");
    query(_DB_INFO.args(NAME) + "//attributeindex/text()", "ON");
    query(_DB_INFO.args(NAME) + "//fulltextindex/text()", "ON");
  }