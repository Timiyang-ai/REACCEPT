@Test
  public void listDetails() {
    query(_DB_LIST_DETAILS.args() + "/@resources/string()", "1");

    query(_DB_ADD.args(NAME, "\"<a/>\"", "xml"));
    query(_DB_STORE.args(NAME, "raw", "bla"));

    final String xmlCall = _DB_LIST_DETAILS.args(NAME, "xml");
    query(xmlCall + "/@raw/data()", "false");
    query(xmlCall + "/@content-type/data()", MimeTypes.APP_XML);
    query(xmlCall + "/@modified-date/xs:dateTime(.)");
    query(xmlCall + "/@size/data()", "");
    query(xmlCall + "/text()", "xml");

    final String rawCall = _DB_LIST_DETAILS.args(NAME, "raw");
    query(rawCall + "/@raw/data()", "true");
    query(rawCall + "/@content-type/data()", MimeTypes.APP_OCTET);
    query(rawCall + "/@modified-date/xs:dateTime(.) > " +
        "xs:dateTime('1971-01-01T00:00:01')", "true");
    query(rawCall + "/@size/data()", "3");
    query(rawCall + "/text()", "raw");

    query(_DB_LIST_DETAILS.args(NAME, "test"), "");
    error(_DB_LIST_DETAILS.args("mostProbablyNotAvailable"), Err.BXDB_OPEN_X);
  }