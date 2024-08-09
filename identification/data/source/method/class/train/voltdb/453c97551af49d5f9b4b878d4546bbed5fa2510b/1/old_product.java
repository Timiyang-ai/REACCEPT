@Override
    public boolean first() throws SQLException {
        checkClosed();
        if (rowCount == 0)
            return false;
        try {
            if (table.getActiveRowIndex() != 0)
                table.resetRowPosition();
            return table.advanceToRow(0);
        } catch (Exception x) {
            throw SQLError.get(x);
        }
    }