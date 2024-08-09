@Test
  public final void export() {
    final IO io = IO.get("export.xml");
    no(new Export(io.path()));
    ok(new CreateDB(NAME, FILE));
    ok(new Export(".", io.name()));
    ok(io.exists());
    ok(io.delete());
  }