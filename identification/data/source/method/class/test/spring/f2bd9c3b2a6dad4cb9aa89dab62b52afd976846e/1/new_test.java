@Test(expected=UnsupportedOperationException.class)
    public void testRollback() throws SQLException {
        try {
            sqlSessionTemplate.rollback();
        } finally {
            connection.close();    
        }
    }