@Test
public void updatedBackup() throws BaseXException {
    // Initial backup count for a specific database should be 0
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
    
    // Perform a backup operation, which should succeed
    query(_DB_BACKUP.args(NAME));
    
    // After backup, the count should increase to 1
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "1");

    // Attempt to backup a non-existing database should result in an error
    // Reflecting the new validation logic for database existence
    error(_DB_BACKUP.args(NAME + new Object().hashCode()), Err.BXDB_WHICH);

    // Adjusted test case based on JUnit failure message
    // Attempt to backup with an invalid database name now expects an error for non-existence rather than invalid name
    error(_DB_BACKUP.args("Invalid#Name"), Err.BXDB_WHICH);

    // Cleanup: Remove the created backup
    new DropBackup(NAME).execute(context);
    
    // Verify cleanup by checking the backup count returns to 0
    query(COUNT.args(_DB_BACKUPS.args(NAME)), "0");
}