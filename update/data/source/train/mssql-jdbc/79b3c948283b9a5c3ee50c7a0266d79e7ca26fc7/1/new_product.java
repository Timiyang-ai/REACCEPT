public java.sql.ResultSet getColumnPrivileges(String catalog,
            String schema,
            String table,
            String col) throws SQLServerException, SQLTimeoutException {
        if (loggerExternal.isLoggable(Level.FINER) && Util.IsActivityTraceOn()) {
            loggerExternal.finer(toString() + " ActivityId: " + ActivityCorrelator.getNext().toString());
        }
        checkClosed();
        // column_privileges supports columns being escaped.
        col = EscapeIDName(col);
        /*
         * sp_column_privileges [ @table_name = ] 'table_name' [ , [ @table_owner = ] 'table_owner' ] [ , [ @table_qualifier = ] 'table_qualifier' ] [
         * , [ @column_name = ] 'column' ]
         */

        String[] arguments = new String[4];
        arguments[0] = table;
        arguments[1] = schema;
        arguments[2] = catalog;
        arguments[3] = col;
        return getResultSetWithProvidedColumnNames(catalog, CallableHandles.SP_COLUMN_PRIVILEGES, arguments, getColumnPrivilegesColumnNames);
    }