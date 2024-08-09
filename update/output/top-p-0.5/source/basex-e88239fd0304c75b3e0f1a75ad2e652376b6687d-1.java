@Test
public void createDbSuccessfully() {
    final String dbname = "SandboxTestDBCreationSuccess";
    try {
        // Attempt to create the database without specifying a non-existent resource or complex options
        String creationResult = query(_DB_CREATE.args(dbname));
        
        // Verify the database creation was successful
        assertNotNull("Database creation result should not be null", creationResult);
        
        // Further verification to ensure the database exists could be performed here
        String existsResult = query(_DB_EXISTS.args(dbname));
        assertEquals("true", existsResult);
        
    } finally {
        // Ensure the database is dropped after the test to clean up
        try {
            query(_DB_DROP.args(dbname));
        } catch (Exception e) {
            // Handle the case where the database might not have been created or already dropped
            System.out.println("Cleanup failed: " + e.getMessage());
        }
    }
}