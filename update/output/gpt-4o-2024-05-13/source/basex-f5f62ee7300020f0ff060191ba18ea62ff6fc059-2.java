@Test
public void backup() throws BaseXException {
  query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
  query(_DB_BACKUP.args(NAME));
  query(COUNT.args(_DB_BACKUPS.args(NAME)), "1");

  // try to backup non-existing database
  error(_DB_BACKUP.args(NAME + 'x'), Err.BXDB_WHICH);

  // cleanup
  new DropBackup(NAME).execute(context);
  query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
}