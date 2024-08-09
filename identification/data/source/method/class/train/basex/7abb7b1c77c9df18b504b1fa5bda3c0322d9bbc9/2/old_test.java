@Test
  public void dirName() {
    // check with a simple path
    assertEquals(norm(PATH),
        norm(query(_FILE_DIR_NAME.args(PATH1))).toLowerCase(Locale.ENGLISH));
    // check with an empty path
    query(_FILE_DIR_NAME.args(""), ".");
    // check with a path without directory separators
    query(_FILE_DIR_NAME.args(NAME), ".");
  }