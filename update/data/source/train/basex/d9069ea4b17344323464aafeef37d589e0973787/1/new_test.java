@Test
  public final void add() {
    // database must be opened to add files
    no(new Add(FILE));
    ok(new CreateDB(NAME));
    ok(new Add(FILE, "input"));
    ok(new Add(FILE, "input"));
    //ok(new Add(URL, "xml"));
    ok(new Add(FLDR, "xml"));
  }