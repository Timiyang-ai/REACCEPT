@Test
  public void connect() {
    query(conn());
    query(EXISTS.args(' ' + conn()));
    // BXCL0001: connection errors
    error(_CLIENT_CONNECT.args(Text.S_LOCALHOST, DB_PORT, ADMIN, ""), BXCL_CONN_X);
    error(_CLIENT_CONNECT.args("xxx", DB_PORT, ADMIN, ADMIN), BXCL_CONN_X);
  }