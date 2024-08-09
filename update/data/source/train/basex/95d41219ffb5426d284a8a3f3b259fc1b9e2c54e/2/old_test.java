@Test
  public void find() {
    query(_BIN_FIND.args(base64("1122"),   0, base64("11")), 0);
    query(_BIN_FIND.args(base64("1122"),   1, base64("11")), "");
    query(_BIN_FIND.args(base64("112233"), 0, base64("22")), 1);
    // errors
    error(_BIN_FIND.args(base64(""), -1, base64("11")), BIN_NO_X);
    error(_BIN_FIND.args(base64(""), 1, base64("11")),  BIN_OBE_X_X);
    error(_BIN_FIND.args(base64(""), 0, base64("")),    BIN_ESI);
  }