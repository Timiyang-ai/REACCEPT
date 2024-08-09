@AfterAll
    public static void afterAll() throws SQLException {

        stmt.executeUpdate("IF EXISTS (select * from sysobjects where id = object_id(N'" + tableName + "') and OBJECTPROPERTY(id, N'IsTable') = 1)"
                + " DROP TABLE " + tableName);

        if (null != stmt) {
            stmt.close();
        }
        if (null != con) {
            con.close();
        }
    }