@Test
  public final void store() throws IOException {
    session.execute("create db " + DB);
    session.store("X", new ArrayInput("!"));
    check("true", session.query(_DB_IS_RAW.args(DB, "X")).execute());
    session.store("X", new ArrayInput(""));
    check("", session.query(_DB_RETRIEVE.args(DB, "X")).execute());
    session.store("X", new ArrayInput(new byte[] { 0, 1, -1 }));
    check("0001FF", session.query(
        "xs:hexBinary(" + _DB_RETRIEVE.args(DB, "X") + ")").execute());
    session.execute("drop db " + DB);
  }