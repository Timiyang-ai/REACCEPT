@Test
  public void parent() {
    // check with a simple path
    assertEquals(Paths.get(PATH1).getParent().toString() + File.separator,
        query(_FILE_PARENT.args(PATH1)));
    // check with an empty path
    query(EMPTY.args(_FILE_PARENT.args("")), "false");
    // check with a path without directory separators
    query(EMPTY.args(_FILE_PARENT.args(NAME)), "false");
    // check with a path without directory separators
    query(EMPTY.args(_FILE_PARENT.args("/")), "true");
  }