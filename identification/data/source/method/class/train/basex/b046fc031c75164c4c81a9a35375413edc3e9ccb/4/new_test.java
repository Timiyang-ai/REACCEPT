@Test
  public void logs() {
    // no logging data exists in the sandbox
    query(_ADMIN_LOGS.args(), "");
    error(_ADMIN_LOGS.args("2001-01-01"), Err.WHICHRES_X);
  }