@Test
    public void createTableTest() throws SQLException {
        try (PreparedStatement pstmt1 = connection
                .prepareStatement("create table " + AbstractSQLGenerator.escapeIdentifier(tableName) + " (col1 int)");
                PreparedStatement pstmt2 = connection
                        .prepareStatement("drop table " + AbstractSQLGenerator.escapeIdentifier(tableName))) {
            pstmt1.execute();
            pstmt2.execute();
        } catch (SQLException e) {
            fail(TestResource.getResource("R_createDropTableFailed") + TestResource.getResource("R_errorMessage")
                    + e.getMessage());
        }
    }