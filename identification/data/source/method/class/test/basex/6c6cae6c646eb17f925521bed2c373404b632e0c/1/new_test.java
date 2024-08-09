@Test
  public final void createBackup() {
    no(new CreateBackup(NAME));
    ok(new CreateDB(NAME));
    ok(new CreateDB(NAME2));
    ok(new CreateBackup(NAME));
    ok(new Close());
    ok(new Restore(NAME));
    ok(new CreateBackup(NAME));
    ok(new DropBackup(NAME));
    ok(new CreateBackup(NAME + "*"));
    ok(new Restore(NAME2));
    ok(new DropBackup(NAME + "*"));
    no(new Restore(":"));
  }