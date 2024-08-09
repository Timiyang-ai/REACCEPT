@Test
    public void createTableTest() throws SQLException {
        try (Connection con = DriverManager.getConnection(connectionString);
                PreparedStatement pstmt1 = con.prepareStatement(
                        "create table " + AbstractSQLGenerator.escapeIdentifier(tableName) + " (col1 int)");
                PreparedStatement pstmt2 = con
                        .prepareStatement("drop table " + AbstractSQLGenerator.escapeIdentifier(tableName))) {
            pstmt1.execute();
            pstmt2.execute();
        } catch (SQLException e) {
            fail(TestResource.getResource("R_createDropTableFailed") + TestResource.getResource("R_errorMessage")
                    + e.getMessage());
        }
    }