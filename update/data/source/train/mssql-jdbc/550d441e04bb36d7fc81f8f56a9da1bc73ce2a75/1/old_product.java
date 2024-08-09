private static void createTable() throws SQLException {
        String sql = "create table " + tableName + " ( col1 int, col2 varchar(50), col3 varchar(10), col4 int)";
        stmt.execute(sql);
    }