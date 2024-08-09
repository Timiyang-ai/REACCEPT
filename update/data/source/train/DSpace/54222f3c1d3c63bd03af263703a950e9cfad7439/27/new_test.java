@Test
    public void testGetDBConnection() throws SQLException
    {
        DBConnection connection = context.getDBConnection();
        
        assertThat("testGetDBConnection 0", connection, notNullValue());
        assertThat("testGetDBConnection 1", connection.isSessionAlive(), equalTo(true));
    }