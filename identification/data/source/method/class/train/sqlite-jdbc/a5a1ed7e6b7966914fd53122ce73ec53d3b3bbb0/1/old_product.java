public void close() throws SQLException {
        batch = null;
        if (pointer == 0 || db == null) clearRS(); else clearParameters();
    }