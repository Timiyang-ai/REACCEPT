@Test
  public void pathToNative() throws IOException {
    query(_FILE_WRITE.args(PATH1, "()"));
    assertEquals(Paths.get(PATH1).toRealPath().toString(), query(_FILE_PATH_TO_NATIVE.args(PATH1)));
    query(_FILE_PATH_TO_NATIVE.args(PATH + "../" + NAME + '/' + NAME),
        Paths.get(PATH + "../" + NAME + '/' + NAME).toRealPath().toString());
    error(_FILE_PATH_TO_NATIVE.args(PATH1 + NAME), Err.FILE_NOT_FOUND);
  }