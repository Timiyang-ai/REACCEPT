@Test
  public void readText() {
    error(_FILE_READ_TEXT.args(PATH1), Err.FILE_WHICH);
    error(_FILE_READ_TEXT.args(PATH), Err.FILE_DIR);
    query(_FILE_WRITE.args(PATH1, "a\u00e4"));
    query(_FILE_READ_TEXT.args(PATH1), "a\u00e4");
    error(_FILE_READ_TEXT.args(PATH1, "UNKNOWN"), Err.FILE_ENCODING);
    assertEquals(3, query(_FILE_READ_TEXT.args(PATH1, "CP1252")).length());
    query(_FILE_WRITE_BINARY.args(PATH1, "xs:hexBinary('00')"));
    error(_FILE_READ_TEXT.args(PATH1), Err.FILE_IO);
    query("declare option db:checkstrings 'off';" + _FILE_READ_TEXT.args(PATH1), "&#x0;");
    query(_FILE_DELETE.args(PATH1));
  }