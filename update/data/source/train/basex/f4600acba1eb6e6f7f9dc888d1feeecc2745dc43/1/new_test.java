@Test
  public final void delete() {
    // database must be opened to add and delete files
    no(new Delete(FILE));
    ok(new CreateDB(NAME));
    ok(new Delete(FILE));
    ok(new Add("", FILE));
    ok(new Delete(FILE));
    // target need not exist
    ok(new Delete(FILE));
    // delete binary file
    ok(new Store(FN, FILE));
    ok(new Delete(FN));
    ok(new Delete(FN));
  }