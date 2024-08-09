@Test
  public final void store() {
    ok(new CreateDB(NAME));
    ok(new Store(NAME2, FILE));
    // file can be overwritten
    ok(new Store(NAME2, FILE));
    // reject invalid names
    no(new Store("", FILE));
  }