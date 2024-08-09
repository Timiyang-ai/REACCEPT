@Test
    public void setShardingKeyTest() throws TestAbortedException, SQLException {
        assumeTrue(Util.supportJDBC43(connection));
        SQLServerConnection connection43 = (SQLServerConnection43) DriverManager.getConnection(connectionString);
        try {
            connection43.setShardingKey(shardingKey);
        }
        catch (SQLException e) {
            assert (e.getMessage().contains(TestResource.getResource("R_notImplemented")));
        }
        try {
            connection43.setShardingKey(shardingKey, superShardingKey);
        }
        catch (SQLException e) {
            assert (e.getMessage().contains(TestResource.getResource("R_notImplemented")));
        }
       
    }