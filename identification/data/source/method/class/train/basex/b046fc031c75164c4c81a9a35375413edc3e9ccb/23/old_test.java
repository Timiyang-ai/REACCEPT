@Test
  public void search() throws BaseXException {
    // check index results
    query(_FT_SEARCH.args(NAME, "assignments"), "Assignments");
    query(_FT_SEARCH.args(NAME, " ('exercise','1')"), "Exercise 1Exercise 2");
    query(_FT_SEARCH.args(NAME, "<x>1</x>"), "Exercise 1");
    query(_FT_SEARCH.args(NAME, "1"), "Exercise 1");
    query(_FT_SEARCH.args(NAME, "XXX"), "");

    // apply index options to query term
    new Set(MainOptions.STEMMING, true).execute(context);
    new CreateIndex("fulltext").execute(context);
    contains(_FT_SEARCH.args(NAME, "Exercises") + "/..", "<li>Exercise 1</li>");
    new Set(MainOptions.STEMMING, false).execute(context);
    new CreateIndex(CmdIndex.FULLTEXT).execute(context);

    // check match options
    query(_FT_SEARCH.args(NAME, "Assignments", " map { }"), "Assignments");
    query(_FT_SEARCH.args(NAME, "Azzignments", " map { 'fuzzy':'' }"), "Assignments");
    query(_FT_SEARCH.args(NAME, "Azzignments", " map { 'fuzzy':'no' }"), "");
    // check search modes
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':'phrase' }"), "");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':'all' }"), "");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':'any' }"), "");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':'any word' }"),
        "Exercise 1Exercise 2");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':'all words' }"),
        "Exercise 1");

    query(_FT_SEARCH.args(NAME, "databases xml", " map { 'mode':'all words'," +
        "'distance':map {'min':0,'max':1} }"), "Databases and XML");
    query(_FT_SEARCH.args(NAME, "databases xml", " map { 'mode':'all words'," +
        "'distance':map {'max':0} }"), "");
    query(_FT_SEARCH.args(NAME, "databases xml", " map { 'mode':'all words'," +
        "'window':map {'size':3} }"), "Databases and XML");

    // check buggy options
    error(_FT_SEARCH.args(NAME, "x", " map { 'x':'y' }"), Err.INVALIDOPT);
    error(_FT_SEARCH.args(NAME, "x", " map { 'mode':'' }"), Err.INVALIDOPT);
    error(_FT_SEARCH.args(NAME, "x", " 1"), Err.ELMMAPTYPE);
  }