private GridSqlInsert parseInsert(Insert insert) {
        GridSqlInsert res = (GridSqlInsert)h2ObjToGridObj.get(insert);

        if (res != null)
            return res;

        res = new GridSqlInsert();
        h2ObjToGridObj.put(insert, res);

        Table srcTbl = INSERT_TABLE.get(insert);
        GridSqlElement tbl = parseTable(srcTbl);

        res.into(tbl).
            direct(INSERT_DIRECT.get(insert)).
            sorted(INSERT_SORTED.get(insert));

        Column[] srcCols = INSERT_COLUMNS.get(insert);
        GridSqlColumn[] cols = new GridSqlColumn[srcCols.length];

        for (int i = 0; i < srcCols.length; i++) {
            cols[i] = new GridSqlColumn(srcCols[i], tbl, null, null, srcCols[i].getName());

            cols[i].resultType(fromColumn(srcCols[i]));
        }

        res.columns(cols);

        List<Expression[]> srcRows = COMMAND_WITH_VALUES_VALS_EXPRESSIONS.get(insert);
        if (!srcRows.isEmpty()) {
            List<GridSqlElement[]> rows = new ArrayList<>(srcRows.size());
            for (Expression[] srcRow : srcRows) {
                GridSqlElement[] row = new GridSqlElement[srcRow.length];

                for (int i = 0; i < srcRow.length; i++) {
                    row[i] = parseExpression(srcRow[i], false);

                    if (row[i] == null) {
                        throw new IgniteSQLException("DEFAULT values are unsupported for MERGE.",
                            IgniteQueryErrorCode.UNSUPPORTED_OPERATION);
                    }
                }

                rows.add(row);
            }
            res.rows(rows);
        }
        else {
            res.rows(Collections.<GridSqlElement[]>emptyList());
            res.query(parseQuery(INSERT_QUERY.get(insert)));
        }

        return res;
    }