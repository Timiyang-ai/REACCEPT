@Test
  public void createBackup() {
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
    query(_DB_CREATE_BACKUP.args(NAME));
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "1");

    // invalid name
    error(_DB_CREATE_BACKUP.args(" ''"), Err.BXDB_NAME);
    // try to backup non-existing database
    error(_DB_CREATE_BACKUP.args(NAME + 'x'), Err.BXDB_WHICH);
  }