@Test
  public final void add() {
    // database must be opened to add files
    no(new Add(FILE));
    ok(new CreateDB(NAME));
    ok(new Add(FILE, FN));
    ok(new Add(FILE, FN, "target"));
    no(new Add(FILE, "\\"));
    no(new Add(FILE, "/"));
  }