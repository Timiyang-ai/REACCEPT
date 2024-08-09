@Test
  public final void delete() {
    // database must be opened to add files
    no(new Delete(FILE));
    ok(new CreateDB(NAME));
    // target need not exist
    ok(new Delete(FILE));
    ok(new Add("", FILE));
    ok(new Delete(FILE));
    ok(new Delete(FILE));
  }