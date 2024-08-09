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
    query(_FT_SEARCH.args(NAME, "Assignments", " { }"), "Assignments");
    query(_FT_SEARCH.args(NAME, "Azzignments", " { 'fuzzy':'' }"), "Assignments");
    query(_FT_SEARCH.args(NAME, "Azzignments", " { 'fuzzy':'no' }"), "");
    // check search modes
    query(_FT_SEARCH.args(NAME, "1 Exercise", " { 'mode':'phrase' }"), "");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " { 'mode':'all' }"), "");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " { 'mode':'any' }"), "");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " { 'mode':'any word' }"),
        "Exercise 1Exercise 2");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " { 'mode':'all words' }"),
        "Exercise 1");

    // check buggy options
    error(_FT_SEARCH.args(NAME, "x", " { 'x':'y' }"), Err.INVALIDOPT);
    error(_FT_SEARCH.args(NAME, "x", " { 'mode':'' }"), Err.INVALIDOPT);
    error(_FT_SEARCH.args(NAME, "x", " 1"), Err.ELMMAPTYPE);
  }