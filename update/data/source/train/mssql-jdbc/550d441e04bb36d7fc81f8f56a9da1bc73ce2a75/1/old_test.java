@Test
    public void createTableTest() throws SQLException {
        try {
            pstmt1 = con.prepareStatement("create table x (col1 int)");
            pstmt2 = con.prepareStatement("drop table x");
            pstmt1.execute();
            pstmt2.execute();
        } catch (SQLException e) {
            fail(TestResource.getResource("R_createDropTableFailed") + TestResource.getResource("R_errorMessage")
                    + e.getMessage());
        }

        finally {
            if (null != pstmt1) {
                pstmt1.close();
            }
            if (null != pstmt2) {
                pstmt2.close();
            }
        }
    }