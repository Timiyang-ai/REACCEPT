@Test(expected=UnsupportedOperationException.class)
    public void testCommit() throws SQLException {
        try {
            sqlSessionTemplate.commit();
        } finally {
            connection.close();    
        }
    }