@Test
  public final void rename() {
    // database must be opened to rename paths
    no(new Rename(FILE, "xxx"));
    ok(new CreateDB(NAME, FILE));
    // target path must not be empty
    no(new Rename(FN, "/"));
    no(new Rename(FN, ""));
    ok(new Rename(FILE, FILE));
    ok(new Rename(FILE, "xxx"));
    // source need not exist
    ok(new Rename(FILE, "xxx"));
  }