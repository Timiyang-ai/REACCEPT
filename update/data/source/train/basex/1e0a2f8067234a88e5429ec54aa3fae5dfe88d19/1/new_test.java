@Test
  public void backups() throws BaseXException {
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
    new CreateBackup(NAME).execute(context);
    query(COUNT.args(_DB_BACKUPS.args()), "1");
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "1");
    query(COUNT.args(_DB_BACKUPS.args(NAME) + "/(@database, @date, @size)"), "3");
    query(COUNT.args(_DB_BACKUPS.args(NAME + 'X')), "0");
    new DropBackup(NAME).execute(context);
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
  }