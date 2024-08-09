@Test
public final void backup() {
    // Test creating a backup when no database exists
    no(new CreateBackup(NAME));
    
    // Test creating databases
    ok(new CreateDB(NAME));
    ok(new CreateDB(NAME2));
    
    // Test creating a backup for the first database
    ok(new CreateBackup(NAME));
    
    // Test closing the database
    ok(new Close());
    
    // Test restoring the first database from the backup
    ok(new Restore(NAME));
    
    // Test creating another backup for the first database
    ok(new CreateBackup(NAME));
    
    // Test dropping the backup for the first database
    ok(new DropBackup(NAME));
    
    // Test creating a backup for all databases matching the pattern
    ok(new CreateBackup(NAME + "*"));
    
    // Test restoring the second database from the backup
    ok(new Restore(NAME2));
    
    // Test dropping the backup for all databases matching the pattern
    ok(new DropBackup(NAME + "*"));
    
    // Test restoring a non-existent database (should fail)
    no(new Restore(":"));
}