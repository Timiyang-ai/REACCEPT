@Test
    public void testCreateStatement() throws SQLException {

        try {
            conn = new DBConnection(connectionString);
            stmt = conn.createStatement();
            // SELECT * FROM <table1>
            String query = "SELECT * FROM " + table1.getEscapedTableName() + ";";
            rs = stmt.executeQuery(query);
            rs.verify(table1);
        }
        finally {

            terminateVariation();
        }
    }