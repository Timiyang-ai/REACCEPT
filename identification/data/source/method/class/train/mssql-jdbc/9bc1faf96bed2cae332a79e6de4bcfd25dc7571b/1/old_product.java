private static void createTable() throws SQLException {
        try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
            String sql = "create table " + AbstractSQLGenerator.escapeIdentifier(tableName)
                    + " ( col1 int, col2 varchar(50), col3 varchar(10), col4 int)";
            stmt.execute(sql);
        }
    }