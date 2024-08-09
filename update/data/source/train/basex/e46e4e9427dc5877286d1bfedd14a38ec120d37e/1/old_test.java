@Test
  public void testSearch() throws BaseXException, QueryException {
    // test arguments
    final String fun = check(FunDef.SEARCH);

    // check index results
    query(fun + "(., 'assignments')", "Assignments");
    query(fun + "(., 'XXX')", "");

    // apply index options to query term
    new Set("stemming", true).execute(CONTEXT);
    new CreateIndex("fulltext").execute(CONTEXT);
    contains(fun + "(., 'Exercises')/..", "<li>Exercise 1</li>");
    new Set("stemming", false).execute(CONTEXT);
    new CreateIndex("fulltext").execute(CONTEXT);
  }