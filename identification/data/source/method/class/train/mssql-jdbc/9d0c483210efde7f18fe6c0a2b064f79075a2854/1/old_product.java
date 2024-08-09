public DBStatement createStatement(int type, int concurrency) throws SQLException {
        Statement product = ((Connection) product()).createStatement(type, concurrency);
        DBStatement wrapper = new DBStatement(this, product, type, concurrency, _holdability);

        // State
        wrapper._cursortype = type;
        wrapper._concurrency = concurrency;

        return wrapper;
    }