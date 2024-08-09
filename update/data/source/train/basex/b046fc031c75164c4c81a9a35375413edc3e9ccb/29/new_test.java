@Test
  public void contentType() {
    query(_DB_ADD.args(NAME, "\"<a/>\"", "xml"));
    query(_DB_STORE.args(NAME, "raw", "bla"));
    query(_DB_CONTENT_TYPE.args(NAME, "xml"), MimeTypes.APP_XML);
    query(_DB_CONTENT_TYPE.args(NAME, "raw"), MimeTypes.APP_OCTET);
    error(_DB_CONTENT_TYPE.args(NAME, "test"), Err.WHICHRES_X);
  }