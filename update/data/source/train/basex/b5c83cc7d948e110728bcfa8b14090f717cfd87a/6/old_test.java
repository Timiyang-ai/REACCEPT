@Test
  public void baseName() {
    // check with a simple path
    query(_FILE_BASE_NAME.args(PATH1), NAME);
    // check with a path ending with a directory separator
    query(_FILE_BASE_NAME.args(PATH1 + File.separator), NAME);
    // check with a path consisting only of directory separators
    query(_FILE_BASE_NAME.args("//"), "");
    // check with empty string path
    query(_FILE_BASE_NAME.args(""), ".");
    // check using a suffix
    query(_FILE_BASE_NAME.args(PATH1 + File.separator + "test.xml", ".xml"), "test");
  }