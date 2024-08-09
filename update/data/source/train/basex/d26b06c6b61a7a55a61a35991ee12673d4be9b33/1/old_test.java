@Test
  public final void restore() {
    no(new Restore(NAME));
    ok(new CreateDB(NAME));
    ok(new CreateBackup(NAME));
    ok(new Restore(NAME));
    no(new Restore(":"));
    ok(new DropBackup(NAME));
    no(new Restore(NAME));
    ok(new Open(NAME));
    no(new Restore(NAME));
    ok(new XQuery("."));
    ok(new CreateDB("test-1"));
    ok(new CreateBackup("test-1"));
    ok(new Restore("test-1"));
    ok(new DropBackup("test"));
    no(new Restore("test"));
    ok(new DropBackup("test-1"));
    ok(new DropDB("test-1"));
    // deleting a backup passing the exact backup name as argument
    ok(new CreateDB(NAME));
    ok(new CreateBackup(NAME));
    ok(new DropBackup(CONTEXT.getDatabases().listBackups().get(0)));
    assertEquals(0, CONTEXT.getDatabases().listBackups(NAME).size());
  }