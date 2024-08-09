@Test
  public void restore() throws BaseXException {
    new Close().execute(context);

    // backup and restore file
    query(_DB_CREATE_BACKUP.args(NAME));
    query(_DB_RESTORE.args(NAME));
    query(_DB_RESTORE.args(NAME));

    // drop backups
    query(_DB_DROP_BACKUP.args(NAME));
    error(_DB_RESTORE.args(NAME), Err.BXDB_NOBACKUP);

    // invalid names
    error(_DB_RESTORE.args(" ''"), Err.BXDB_NAME);
  }