@Test
    public void setCurrentSchema() throws Exception {
        Connection connection = createDataSource().getConnection();
        PostgreSQLDbSupport dbSupport = new PostgreSQLDbSupport(connection);
        Schema schema = dbSupport.getSchema("search_path_test");
        schema.create();
        dbSupport.setCurrentSchema(dbSupport.getSchema("search_path_test"));
        String searchPath = dbSupport.getJdbcTemplate().queryForString("SHOW search_path");
        assertEquals("search_path_test, \"$user\", public", searchPath);
        schema.drop();
        JdbcUtils.closeConnection(connection);
    }