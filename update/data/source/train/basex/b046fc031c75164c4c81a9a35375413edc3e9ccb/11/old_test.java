@Test
  public void createDir() {
    query(_FILE_CREATE_DIR.args(PATH1));
    query(_FILE_CREATE_DIR.args(PATH1));
    query(_FILE_CREATE_DIR.args(PATH3));
    query(_FILE_DELETE.args(PATH1, "true()"));
    query(_FILE_WRITE.args(PATH1, "()"));
    error(_FILE_CREATE_DIR.args(PATH1), Err.FILE_EXISTS);
    error(_FILE_CREATE_DIR.args(PATH3), Err.FILE_EXISTS);
    query(_FILE_DELETE.args(PATH1));
  }