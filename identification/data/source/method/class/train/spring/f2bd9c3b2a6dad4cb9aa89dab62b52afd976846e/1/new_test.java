@Test(expected=UnsupportedOperationException.class)
    public void testClose() throws SQLException {
        try {
            sqlSessionTemplate.close();
        } finally {
            connection.close();    
        }
    }