@Test
  public void append() {
    error(_FILE_APPEND.args(PATH, "()"), Err.FILE_IS_DIR);
    error(_FILE_APPEND.args(PATH4, "()"), Err.FILE_NO_DIR);

    query(_FILE_APPEND.args(PATH1, "0"));
    query(_FILE_SIZE.args(PATH1), "1");
    query(_FILE_APPEND.args(PATH1, "0", "()"));
    query(_FILE_SIZE.args(PATH1), "2");
    query(_FILE_DELETE.args(PATH1));

    query(_FILE_APPEND.args(PATH1, "a\u00e4",
        serialParams("<encoding value='CP1252'/>")));
    query(_FILE_READ_TEXT.args(PATH1, "CP1252"), "a\u00e4");
    query(_FILE_DELETE.args(PATH1));

    query(_FILE_APPEND.args(PATH1, "\"<a/>\"", serialParams("<method value='text'/>")));
    query(_FILE_READ_TEXT.args(PATH1), "&lt;a/&gt;");
    query(_FILE_DELETE.args(PATH1));
  }