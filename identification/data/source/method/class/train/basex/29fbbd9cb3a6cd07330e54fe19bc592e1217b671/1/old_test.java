@Test
  public void logs() {
    // no loggin data exists in the sandbox
    query(_ADMIN_LOGS.args(), "");
    query(_ADMIN_LOGS.args("2001-01-01"), "");
  }