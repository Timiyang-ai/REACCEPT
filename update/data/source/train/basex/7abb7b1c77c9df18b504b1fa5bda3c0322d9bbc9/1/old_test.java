@Test
  public void list() {
    error(_FILE_LIST.args(PATH1), Err.FILE_NODIR);
    query(_FILE_WRITE.args(PATH1, "()"));
    error(_FILE_LIST.args(PATH1), Err.FILE_NODIR);
    contains(_FILE_LIST.args(PATH), NAME);
    contains(_FILE_LIST.args(PATH, "false()"), NAME);
    contains(_FILE_LIST.args(PATH, "false()", NAME), NAME);
    query(_FILE_LIST.args(PATH, "false()", "XXX"), "");
    query(_FILE_DELETE.args(PATH1));
    // check recursive paths
    query(_FILE_CREATE_DIR.args(PATH1));
    query(_FILE_CREATE_DIR.args(PATH3));
    query(_FILE_WRITE.args(PATH4, "()"));
    contains(_FILE_LIST.args(PATH1, "true()"), "y");
    query(_FILE_LIST.args(PATH1, "true()", "x"), "x");
  }