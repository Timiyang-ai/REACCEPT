@Test
  public void parent() {
    // check with a simple path
    assertEquals(norm(PATH), norm(query(_FILE_PARENT.args(PATH1))).toLowerCase(Locale.ENGLISH));
    // check with an empty path
    query(EMPTY.args(_FILE_PARENT.args("")), "false");
    // check with a path without directory separators
    query(EMPTY.args(_FILE_PARENT.args(NAME)), "false");
    // check with a path without directory separators
    query(EMPTY.args(_FILE_PARENT.args("/")), "true");
  }