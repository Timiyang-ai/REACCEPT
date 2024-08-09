private void batch(int pos, Object value) throws SQLException {
        checkOpen();
        if (batch == null) {
            batch = new Object[paramCount];
        }
        batch[batchPos + pos - 1] = value;
    }