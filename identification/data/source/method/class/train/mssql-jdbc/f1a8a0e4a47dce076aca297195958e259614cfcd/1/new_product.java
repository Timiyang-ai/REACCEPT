@AfterAll
    public static void afterAll() throws SQLException {
        Utils.dropTableIfExists(tableName, stmt);
        Utils.dropTableIfExists(destTableName, stmt);

        if (null != stmt) {
            stmt.close();
        }
        if (null != con) {
            con.close();
        }
    }