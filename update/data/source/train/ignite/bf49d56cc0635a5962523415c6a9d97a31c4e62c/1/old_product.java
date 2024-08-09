@Override public ResultSet getTables(String catalog, String schemaPtrn, String tblNamePtrn,
        String[] tblTypes) throws SQLException {
        updateMetaData();

        List<List<?>> rows = new LinkedList<>();

        if (isValidCatalog(catalog) && (tblTypes == null || Arrays.asList(tblTypes).contains("TABLE"))) {
            for (Map.Entry<String, Map<String, Map<String, ColumnInfo>>> schema : meta.entrySet()) {
                if (matches(schema.getKey(), schemaPtrn)) {
                    for (String tbl : schema.getValue().keySet()) {
                        if (matches(tbl, tblNamePtrn))
                            rows.add(tableRow(schema.getKey(), tbl));
                    }
                }
            }
        }

        return new JdbcResultSet(true, null,
            conn.createStatement0(),
            Collections.<String>emptyList(),
            Arrays.asList("TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "TABLE_TYPE", "REMARKS", "TYPE_CAT",
                "TYPE_SCHEM", "TYPE_NAME", "SELF_REFERENCING_COL_NAME", "REF_GENERATION"),
            Arrays.asList(String.class.getName(), String.class.getName(), String.class.getName(),
                String.class.getName(), String.class.getName(), String.class.getName(), String.class.getName(),
                String.class.getName(), String.class.getName(), String.class.getName()),
            rows, true
        );
    }