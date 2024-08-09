@Test
  public final void create() throws IOException {
    session.create(DB, new ArrayInput(""));
    check("", session.query("doc('" + DB + "')").execute());
    session.create(DB, new ArrayInput("<X/>"));
    check("<X/>", session.query("doc('" + DB + "')").execute());
  }