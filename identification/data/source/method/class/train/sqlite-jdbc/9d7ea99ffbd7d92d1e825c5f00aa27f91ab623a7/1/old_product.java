public void clearParameters() throws SQLException {
        checkOpen();
        db.reset(pointer);
        clearBatch();
    }