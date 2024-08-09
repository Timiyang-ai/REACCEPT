public void close() throws SQLException {
        batch = null;
        clearParameters();
        super.close();
    }