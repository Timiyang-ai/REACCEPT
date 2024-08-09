@Test
  public final void replace() throws IOException {
    session.execute("create db " + DB);
    check("0", session.query("count(" + _DB_OPEN.args(DB) + ')').execute());
    session.replace(DB, new ArrayInput("<X/>"));
    check("1", session.query("count(" + _DB_OPEN.args(DB) + ')').execute());
    session.replace(DB + '2', new ArrayInput("<X/>"));
    check("2", session.query("count(" + _DB_OPEN.args(DB) + ')').execute());
    session.replace(DB + '2', new ArrayInput("<X/>"));
    check("2", session.query("count(" + _DB_OPEN.args(DB) + ')').execute());
  }