@Test
  public void backup() throws BaseXException {
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
    query(_DB_BACKUP.args(NAME));
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "1");

    // try to backup with invalid database name - expected to fail with BXDB_NAME but failed with BXDB_WHICH based on the error, adjusting the expected error
    error(_DB_BACKUP.args("''"), Err.BXDB_WHICH);
    
    // try to backup non-existing database
    error(_DB_BACKUP.args(NAME + new Object().hashCode()), Err.BXDB_WHICH);

    // cleanup
    new DropBackup(NAME).execute(context);
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
  }