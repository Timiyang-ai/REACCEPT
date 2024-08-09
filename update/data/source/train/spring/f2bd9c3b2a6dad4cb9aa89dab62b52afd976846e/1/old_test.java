@Test
    public void testClose() throws SQLException {
        sqlSessionTemplate.close();
        assertFalse(connection.isClosed());

        connection.close();
    }