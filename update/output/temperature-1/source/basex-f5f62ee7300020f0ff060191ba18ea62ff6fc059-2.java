@Test
public void backup() throws BaseXException {
    // Check initial backup count for the database
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");

    // Perform a backup operation
    query(_DB_BACKUP.args(NAME));

    // Verify the backup count has increased to 1
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "1");

    // Attempt to backup a non-existing database, expecting an error
    String nonExistingDBName = NAME + new Object().hashCode();
    error(_DB_BACKUP.args(nonExistingDBName), Err.BXDB_WHICH);

    // Cleanup: Drop the created backup
    new DropBackup(NAME).execute(context);

    // Verify the backup count has returned to 0 after cleanup
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
}