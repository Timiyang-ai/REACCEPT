@Test
  public void connect() {
    query(conn());
    query(EXISTS.args(' ' + conn()));
    // BXCL0001: connection errors
    error(_CLIENT_CONNECT.args(Text.S_LOCALHOST, 9999, ADMIN, ""), BXCL_CONN_X);
    error(_CLIENT_CONNECT.args("xxx", 9999, ADMIN, ADMIN), BXCL_CONN_X);
  }