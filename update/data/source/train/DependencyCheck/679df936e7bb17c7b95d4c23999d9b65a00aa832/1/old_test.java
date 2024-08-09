@Test
    public void testOpen() {
        try {
            CveDB instance = new CveDB();
            instance.open();
            instance.close();
        } catch (DatabaseException ex) {
            System.out.println("Unable to connect to the My SQL database; verify that the db server is running and that the schema has been generated");
            fail(ex.getMessage());
        }
    }