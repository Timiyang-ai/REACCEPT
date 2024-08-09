@Test
  public final void export() {
    final IO io = IO.get("input.xml");
    no(new Export(io.path()));
    ok(new CreateDB(NAME, FILE));
    ok(new Export("."));
    ok(io.exists());
    ok(io.delete());
  }