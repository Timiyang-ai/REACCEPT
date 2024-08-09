@Test
  public final void create() throws IOException {
    session.create(NAME, new ArrayInput(""));
    assertEqual("", session.query("doc('" + NAME + "')").execute());
    session.create(NAME, new ArrayInput("<X/>"));
    assertEqual("<X/>", session.query("doc('" + NAME + "')").execute());
  }