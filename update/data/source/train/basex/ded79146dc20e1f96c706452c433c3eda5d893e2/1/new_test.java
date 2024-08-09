@Test
  public void contentType() {
    query(_DB_ADD.args(NAME, "\"<a/>\"", "xml"));
    query(_DB_STORE.args(NAME, "raw", "bla"));
    query(_DB_CONTENT_TYPE.args(NAME, "xml"), MediaType.APPLICATION_XML.toString());
    query(_DB_CONTENT_TYPE.args(NAME, "raw"), MediaType.APPLICATION_OCTET_STREAM.toString());
    error(_DB_CONTENT_TYPE.args(NAME, "test"), WHICHRES_X);
  }