@Test
  public void connect() {
    query(conn());
    query(EXISTS.args(' ' + conn()));
    // BXCL0001: connection errors
    error(_CLIENT_CONNECT.args(Text.S_LOCALHOST, 9999, Text.S_ADMIN, ""), Err.BXCL_CONN_X);
    error(_CLIENT_CONNECT.args("xxx", 9999, Text.S_ADMIN, Text.S_ADMIN), Err.BXCL_CONN_X);
  }