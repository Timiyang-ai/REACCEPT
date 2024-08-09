@Test
  public void dropBackup() {
    // create and drop backup does not exist
    query(_DB_CREATE_BACKUP.args(NAME));
    query(_DB_DROP_BACKUP.args(NAME));
    // backup file does not exist
    error(_DB_DROP_BACKUP.args(NAME), Err.BXDB_WHICHBACK);
    // check if drop is called before create
    error(_DB_CREATE_BACKUP.args(NAME) + ',' + _DB_DROP_BACKUP.args(NAME), Err.BXDB_WHICHBACK);

    // [LK] more tests... EclEmma may help
  }