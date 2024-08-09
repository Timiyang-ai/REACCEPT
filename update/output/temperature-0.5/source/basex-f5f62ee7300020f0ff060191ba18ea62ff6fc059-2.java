@Test
public void backup() throws BaseXException {
  query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
  query(_DB_BACKUP.args(NAME));
  query(COUNT.args(_DB_BACKUPS.args(NAME)), "1");

  // try to backup non-existing database
  error(_DB_BACKUP.args(NAME + new Object().hashCode()), Err.BXDB_WHICH);

  // try to backup with invalid database name, expecting BXDB_WHICH error due to production code behavior
  error(_DB_BACKUP.args("Invalid#Name"), Err.BXDB_WHICH);

  // cleanup
  new DropBackup(NAME).execute(context);
  query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
}