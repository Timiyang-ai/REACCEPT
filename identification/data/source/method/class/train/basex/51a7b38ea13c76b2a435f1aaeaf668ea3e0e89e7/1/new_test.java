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
    final String[] bopt = { "textindex", "attrindex", "tokenindex", "ftindex", "stemming",
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
    final BooleanOption[] indexes = { MainOptions.TEXTINDEX, MainOptions.ATTRINDEX,
        MainOptions.TOKENINDEX, MainOptions.FTINDEX };
    final StringOption[] includes = { MainOptions.TEXTINCLUDE, MainOptions.ATTRINCLUDE,
        MainOptions.TOKENINCLUDE, MainOptions.FTINCLUDE };
    for(final BooleanOption bo : indexes) query(_DB_INFO.args(NAME) +
        "//" + bo.name().toLowerCase(Locale.ENGLISH) + "/text()", "false");
    for(final StringOption so : includes) query(_DB_INFO.args(NAME) +
        "//" + so.name().toLowerCase(Locale.ENGLISH) + "/text()", "");

    execute(new Open(NAME));
    final CmdIndex[] cis = { CmdIndex.TEXT, CmdIndex.ATTRIBUTE, CmdIndex.TOKEN, CmdIndex.FULLTEXT };
    for(final StringOption so : includes) set(so, "a");
    for(final CmdIndex ci : cis) execute(new CreateIndex(ci));
    execute(new Close());

    query(_DB_OPTIMIZE.args(NAME));
    for(final BooleanOption bo : indexes) query(_DB_INFO.args(NAME) +
        "//" + bo.name().toLowerCase(Locale.ENGLISH) + "/text()", "true");
    for(final StringOption so : includes) query(_DB_INFO.args(NAME) +
        "//" + so.name().toLowerCase(Locale.ENGLISH) + "/text()", "a");

    execute(new Open(NAME));
    for(final StringOption so : includes) set(so, "");
    for(final CmdIndex cmd : cis) execute(new DropIndex(cmd));
    execute(new Close());

    query(_DB_OPTIMIZE.args(NAME));
    for(final BooleanOption bo : indexes) query(_DB_INFO.args(NAME) +
        "//" + bo.name().toLowerCase(Locale.ENGLISH) + "/text()", "false");
    for(final StringOption so : includes) query(_DB_INFO.args(NAME) +
        "//" + so.name().toLowerCase(Locale.ENGLISH) + "/text()", "");

    query(_DB_OPTIMIZE.args(NAME, true, " map {"
        + "'textindex':true(),'attrindex':true(),'ftindex':true(),'tokenindex':true(),"
        + "'updindex':true(),'textinclude':'a','attrinclude':'a','tokeninclude':'a','ftinclude':'a'"
        + " }"));
    for(final BooleanOption bo : indexes) query(_DB_INFO.args(NAME) +
        "//" + bo.name().toLowerCase(Locale.ENGLISH) + "/text()", "true");
    for(final StringOption so : includes) query(_DB_INFO.args(NAME) +
        "//" + so.name().toLowerCase(Locale.ENGLISH) + "/text()", "a");
    query(_DB_INFO.args(NAME) + "//updindex/text()", "true");
  }