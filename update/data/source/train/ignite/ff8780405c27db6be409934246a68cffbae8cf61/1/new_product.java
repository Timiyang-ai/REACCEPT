@Override public ResultSet getColumns(String catalog, String schemaPtrn, String tblNamePtrn, String colNamePtrn)
        throws SQLException {
        conn.ensureNotClosed();

        final List<JdbcColumnMeta> meta = asList(
            new JdbcColumnMeta(null, null, "TABLE_CAT", String.class),      // 1
            new JdbcColumnMeta(null, null, "TABLE_SCHEM", String.class),    // 2
            new JdbcColumnMeta(null, null, "TABLE_NAME", String.class),     // 3
            new JdbcColumnMeta(null, null, "COLUMN_NAME", String.class),    // 4
            new JdbcColumnMeta(null, null, "DATA_TYPE", Short.class),       // 5
            new JdbcColumnMeta(null, null, "TYPE_NAME", String.class),      // 6
            new JdbcColumnMeta(null, null, "COLUMN_SIZE", Integer.class),   // 7
            new JdbcColumnMeta(null, null, "BUFFER_LENGTH ", Integer.class), // 8
            new JdbcColumnMeta(null, null, "DECIMAL_DIGITS", Integer.class), // 9
            new JdbcColumnMeta(null, null, "NUM_PREC_RADIX", Short.class),  // 10
            new JdbcColumnMeta(null, null, "NULLABLE", Short.class),        // 11
            new JdbcColumnMeta(null, null, "REMARKS", String.class),        // 12
            new JdbcColumnMeta(null, null, "COLUMN_DEF", String.class),     // 13
            new JdbcColumnMeta(null, null, "SQL_DATA_TYPE", Integer.class), // 14
            new JdbcColumnMeta(null, null, "SQL_DATETIME_SUB", Integer.class), // 15
            new JdbcColumnMeta(null, null, "CHAR_OCTET_LENGTH", Integer.class), // 16
            new JdbcColumnMeta(null, null, "ORDINAL_POSITION", Integer.class), // 17
            new JdbcColumnMeta(null, null, "IS_NULLABLE", String.class),    // 18
            new JdbcColumnMeta(null, null, "SCOPE_CATLOG", String.class),   // 19
            new JdbcColumnMeta(null, null, "SCOPE_SCHEMA", String.class),   // 20
            new JdbcColumnMeta(null, null, "SCOPE_TABLE", String.class),    // 21
            new JdbcColumnMeta(null, null, "SOURCE_DATA_TYPE", Short.class), // 22
            new JdbcColumnMeta(null, null, "IS_AUTOINCREMENT", String.class), // 23
            new JdbcColumnMeta(null, null, "IS_GENERATEDCOLUMN ", String.class) // 24
        );

        if (!isValidCatalog(catalog))
            return new JdbcThinResultSet(Collections.emptyList(), meta);

        JdbcMetaColumnsResult res = conn.sendRequest(new JdbcMetaColumnsRequest(schemaPtrn, tblNamePtrn, colNamePtrn)).response();

        List<List<Object>> rows = new LinkedList<>();

        for (int i = 0; i < res.meta().size(); ++i)
            rows.add(columnRow(res.meta().get(i), i + 1));

        return new JdbcThinResultSet(rows, meta);
    }