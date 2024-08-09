@Test
    public void testRollback() throws SQLException {
        sqlSessionTemplate.rollback();
        assertNoRollback();

        sqlSessionTemplate.rollback(true);
        assertNoRollback();

        sqlSessionTemplate.rollback(false);
        assertNoRollback();

        connection.close();
    }