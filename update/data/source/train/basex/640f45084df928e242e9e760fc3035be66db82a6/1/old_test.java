@Test
  public void readBinary() {
    error(_FILE_READ_BINARY.args(PATH1), Err.FILE_WHICH);
    error(_FILE_READ_BINARY.args(PATH), Err.FILE_DIR);
    query(_FILE_WRITE.args(PATH1, "0"));
    query(_FILE_READ_BINARY.args(PATH1), "MA==");
    query(_FILE_WRITE.args(PATH1, "a\u00e4"));
    query(_FILE_READ_BINARY.args(PATH1), "YcOk");
    query(_FILE_WRITE_BINARY.args(PATH1, _CONVERT_STRING_TO_BASE64.args("a\u00e4")));
    query(_FILE_READ_BINARY.args(PATH1), "YcOk");
    query(_FILE_DELETE.args(PATH1));
  }