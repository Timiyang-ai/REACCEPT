@Test
    public void testCommit() throws SQLException {
        sqlSessionTemplate.commit();
        assertNoCommit();

        sqlSessionTemplate.commit(true);
        assertNoCommit();

        sqlSessionTemplate.commit(false);
        assertNoCommit();

        connection.close();
    }