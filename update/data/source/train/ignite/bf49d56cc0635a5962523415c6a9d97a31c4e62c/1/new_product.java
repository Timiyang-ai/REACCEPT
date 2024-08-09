@Override public ResultSet getTables(String catalog, String schemaPtrn, String tblNamePtrn,
        String[] tblTypes) throws SQLException {
        conn.ensureNotClosed();

        List<List<?>> rows = Collections.emptyList();

        boolean areTypesValid = tblTypes == null || Arrays.asList(tblTypes).contains("TABLE");

        if (isValidCatalog(catalog) && areTypesValid) {
            List<JdbcTableMeta> tabMetas = meta.getTablesMeta(schemaPtrn, tblNamePtrn);

            rows = new ArrayList<>(tabMetas.size());

            for (JdbcTableMeta m : tabMetas)
                rows.add(tableRow(m));
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