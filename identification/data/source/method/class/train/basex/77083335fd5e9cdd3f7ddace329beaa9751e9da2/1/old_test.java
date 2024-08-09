@Test
  public void testSearch() throws BaseXException {
    // test wrong arguments
    args("ft:search", (Class<?>) null, String.class);

    // check index results
    query("ft:search(., 'assignments')", "Assignments");
    query("ft:search(., 'XXX')", "");

    // apply index options to query term
    new Set("stemming", true).execute(CTX);
    new CreateIndex("fulltext").execute(CTX);
    contains("ft:search(., 'Exercises')/..", "<li>Exercise 1</li>");
  }