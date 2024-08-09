@Test
  public void search() throws BaseXException {
    // check index results
    query(_FT_SEARCH.args(NAME, "assignments"), "Assignments");
    query(_FT_SEARCH.args(NAME, " ('exercise','1')"), "Exercise 1Exercise 2");
    query(_FT_SEARCH.args(NAME, "<x>1</x>"), "Exercise 1");
    query(_FT_SEARCH.args(NAME, "1"), "Exercise 1");
    query(_FT_SEARCH.args(NAME, "XXX"), "");

    // apply index options to query term
    new Set(Prop.STEMMING, true).execute(context);
    new CreateIndex("fulltext").execute(context);
    contains(_FT_SEARCH.args(NAME, "Exercises") + "/..",
        "<li>Exercise 1</li>");
    new Set(Prop.STEMMING, false).execute(context);
    new CreateIndex("fulltext").execute(context);

    // check match options
    query(_FT_SEARCH.args(NAME, "Assignments", " map {}"), "Assignments");
    query(_FT_SEARCH.args(NAME, "Azzignments", " map { 'fuzzy':='' }"), "Assignments");
    query(_FT_SEARCH.args(NAME, "Azzignments", " map { 'fuzzy':='no' }"), "");
    // check search modes
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':='phrase' }"), "");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':='all' }"), "");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':='any' }"), "");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':='any word' }"),
        "Exercise 1Exercise 2");
    query(_FT_SEARCH.args(NAME, "1 Exercise", " map { 'mode':='all words' }"),
        "Exercise 1");

    // check buggy options
    error(_FT_SEARCH.args(NAME, "x", " map { 'x':='y' }"), Err.ELMOPTION);
    error(_FT_SEARCH.args(NAME, "x", " map { 'mode':='' }"), Err.ELMOPTION);
    error(_FT_SEARCH.args(NAME, "x", " 1"), Err.ELMMAPTYPE);
  }