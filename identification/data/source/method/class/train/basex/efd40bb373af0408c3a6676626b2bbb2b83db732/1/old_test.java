@Test
  public final void create() throws IOException {
    session.create(NAME, new ArrayInput(""));
    check("", session.query("doc('" + NAME + "')").execute());
    session.create(NAME, new ArrayInput("<X/>"));
    check("<X/>", session.query("doc('" + NAME + "')").execute());
  }