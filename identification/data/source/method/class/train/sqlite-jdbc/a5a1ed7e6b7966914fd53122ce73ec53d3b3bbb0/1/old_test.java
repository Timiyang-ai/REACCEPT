    @After
    public void close() throws SQLException {
        stat.close();
        conn.close();
    }