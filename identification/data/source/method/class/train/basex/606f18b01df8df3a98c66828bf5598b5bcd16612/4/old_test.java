@Test
  public void write() {
    error(_FILE_WRITE.args(PATH, "()"), Err.FILE_ID);
    error(_FILE_WRITE.args(PATH4, "()"), Err.FILE_ND);

    query(_FILE_WRITE.args(PATH1, "0"));
    query(_FILE_SIZE.args(PATH1), "1");
    query(_FILE_WRITE.args(PATH1, "0"));
    query(_FILE_SIZE.args(PATH1), "1");
    query(_FILE_DELETE.args(PATH1));

    query(_FILE_WRITE.args(PATH1, "a\u00e4", serialParams("<encoding value='CP1252'/>")));
    query(_FILE_READ_TEXT.args(PATH1, "CP1252"), "a\u00e4");

    query(_FILE_WRITE.args(PATH1, "\"<a/>\"", serialParams("<method value='text'/>")));
    query(_FILE_READ_TEXT.args(PATH1), "&lt;a/&gt;");
    query(_FILE_DELETE.args(PATH1));

    // test spaces in filename
    query(_FILE_WRITE.args(PATH1 + "%20X", ""));
    query(_FILE_EXISTS.args(PATH1 + "%20X"), "true");
    query(_FILE_DELETE.args(PATH1 + "%20X"));
    query(_FILE_EXISTS.args(PATH1 + "%20X"), "false");

    query(_FILE_WRITE.args(PATH1 + " X", ""));
    query(_FILE_EXISTS.args(PATH1 + " X"), "true");
    query(_FILE_DELETE.args(PATH1 + " X"));
    query(_FILE_EXISTS.args(PATH1 + " X"), "false");
  }