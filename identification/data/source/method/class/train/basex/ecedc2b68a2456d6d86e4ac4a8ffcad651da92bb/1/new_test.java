@Test
  public void dropBackup() {
    // create and drop backup
    query(_DB_CREATE_BACKUP.args(NAME));
    query(_DB_DROP_BACKUP.args(NAME));
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");

    // create and drop backup file
    query(_DB_CREATE_BACKUP.args(NAME));
    query(_DB_DROP_BACKUP.args(query(_DB_BACKUPS.args(NAME))));

    // invalid name
    error(_DB_DROP_BACKUP.args(" ''"), Err.BXDB_NAME);
    // backup file does not exist
    error(_DB_DROP_BACKUP.args(NAME), Err.BXDB_WHICHBACK);
    // check if drop is called before create
    error(_DB_CREATE_BACKUP.args(NAME) + ',' + _DB_DROP_BACKUP.args(NAME), Err.BXDB_WHICHBACK);
  }