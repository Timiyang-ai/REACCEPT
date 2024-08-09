@Test
  public void event() {
    error(_DB_EVENT.args("X", "Y"), Err.BXDB_EVENT);
  }