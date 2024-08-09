@Test
    public void testGetDBConnection() throws SQLException
    {
        Connection connection = context.getDBConnection();
        
        assertThat("testGetDBConnection 0", connection, notNullValue());
        assertThat("testGetDBConnection 1", connection.isClosed(), equalTo(false));
    }