    @AfterAll
    public static void afterAll() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            TestUtils.dropTableIfExists(tableName, stmt);
            TestUtils.dropTableIfExists(AbstractSQLGenerator.escapeIdentifier(destTableName), stmt);
        }
    }