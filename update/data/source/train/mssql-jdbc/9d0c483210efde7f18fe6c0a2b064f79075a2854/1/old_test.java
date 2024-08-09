@Test
    public void testCreateStatement() throws SQLException {

        try {
            conn = new DBConnection(connectionString);
            stmt = conn.createStatement();
            // SELECT * FROM <table1>
            String query = "SELECT * FROM " + table1.getEscapedTableName() + ";";
            rs = stmt.executeQuery(query);
            rs.verify(table1);
            // bvt_rs = new bvt_ResultSet(rs);

            // close and verify

            // bvt_rs.verify();

        }
        finally {

            terminateVariation();
        }
    }