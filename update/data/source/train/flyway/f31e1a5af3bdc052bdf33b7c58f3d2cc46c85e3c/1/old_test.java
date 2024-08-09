@Test
    public void setCurrentSchema() throws Exception {
        Connection connection = createDataSource().getConnection();
        VerticaDbSupport dbSupport = new VerticaDbSupport(connection);
        Schema schema = dbSupport.getSchema("search_path_test");
        schema.create();
        dbSupport.setCurrentSchema(dbSupport.getSchema("search_path_test"));
        String searchPath = dbSupport.doGetSearchPath();
        assertEquals("search_path_test, \"$user\", public, v_catalog, v_monitor, v_internal", searchPath);
        schema.drop();
        JdbcUtils.closeConnection(connection);
    }