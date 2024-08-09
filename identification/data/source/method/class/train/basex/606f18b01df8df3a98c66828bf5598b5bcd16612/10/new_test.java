@Test
  public void delete() {
    query(_FILE_CREATE_DIR.args(PATH3));
    query(_FILE_DELETE.args(PATH3));
    query(_FILE_CREATE_DIR.args(PATH3));
    query(_FILE_WRITE.args(PATH4, "()"));
    query(_FILE_DELETE.args(PATH1, "true()"));
    error(_FILE_DELETE.args(PATH1), Err.FILE_NOT_FOUND);
  }