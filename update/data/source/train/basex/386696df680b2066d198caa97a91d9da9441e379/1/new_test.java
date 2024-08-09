@Test
  public final void add() throws IOException {
    session.execute("create db " + DB);
    session.add(DB, new ArrayInput("<X/>"));
    check("1", session.query("count(" + DBOPEN.args(DB) + ")").execute());
    for(int i = 0; i < 9; i++) session.add(DB, new ArrayInput("<X/>"));
    check("10", session.query("count(" + DBOPEN.args(DB) + ")").execute());
  }