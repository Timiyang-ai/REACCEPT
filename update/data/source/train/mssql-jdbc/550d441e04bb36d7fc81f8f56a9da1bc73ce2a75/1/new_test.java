@Test
    public void createTableTest() throws SQLException {
        try (PreparedStatement pstmt1 = con.prepareStatement("create table x (col1 int)");
                PreparedStatement pstmt2 = con.prepareStatement("drop table x")) {
            pstmt1.execute();
            pstmt2.execute();
        } catch (SQLException e) {
            fail(TestResource.getResource("R_createDropTableFailed") + TestResource.getResource("R_errorMessage")
                    + e.getMessage());
        }
    }