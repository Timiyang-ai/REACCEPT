public void clearParameters() throws SQLException {
        checkOpen();
        db.clear_bindings(pointer);
        batch = null;
    }