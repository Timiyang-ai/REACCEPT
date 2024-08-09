@Test
  public void children() {
    query(_FILE_WRITE.args(PATH1, "abcd"));
    error(_FILE_CHILDREN.args(PATH1), Err.FILE_NO_DIR_X);
    error(_FILE_CHILDREN.args(PATH1 + NAME), Err.FILE_NOT_FOUND_X);
    query(_FILE_WRITE.args(PATH1, "()"));
    error(_FILE_CHILDREN.args(PATH1), Err.FILE_NO_DIR_X);
    contains(_FILE_CHILDREN.args(PATH), NAME);
    query(_FILE_DELETE.args(PATH1));
  }