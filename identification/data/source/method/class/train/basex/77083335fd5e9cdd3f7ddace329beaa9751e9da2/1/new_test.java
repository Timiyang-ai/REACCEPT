@Test
  public void testSearch() throws BaseXException {
    // test arguments
    final String fun = check(FunDef.SEARCH, DBNode.class, String.class);

    // check index results
    query(fun + "(., 'assignments')", "Assignments");
    query(fun + "(., 'XXX')", "");

    // apply index options to query term
    new Set("stemming", true).execute(CTX);
    new CreateIndex("fulltext").execute(CTX);
    contains(fun + "(., 'Exercises')/..", "<li>Exercise 1</li>");
  }