private GridSqlMerge parseMerge(Merge merge) {
        GridSqlMerge res = (GridSqlMerge)h2ObjToGridObj.get(merge);

        if (res != null)
            return res;

        res = new GridSqlMerge();
        h2ObjToGridObj.put(merge, res);

        Table srcTbl = MERGE_TABLE.get(merge);
        GridSqlElement tbl = parseTable(srcTbl);

        res.into(tbl);

        Column[] srcCols = MERGE_COLUMNS.get(merge);

        GridSqlColumn[] cols = new GridSqlColumn[srcCols.length];

        for (int i = 0; i < srcCols.length; i++) {
            cols[i] = new GridSqlColumn(srcCols[i], tbl, null, null, srcCols[i].getName());

            cols[i].resultType(fromColumn(srcCols[i]));
        }

        res.columns(cols);

        Column[] srcKeys = MERGE_KEYS.get(merge);

        GridH2Table intoTbl = DmlAstUtils.gridTableForElement(tbl).dataTable();

        GridH2RowDescriptor rowDesc = intoTbl.rowDescriptor();

        GridSqlColumn[] keys = new GridSqlColumn[srcKeys.length];

        for (int i = 0; i < srcKeys.length; i++) {
            String colName = srcKeys[i].getName();

            int colId = intoTbl.getColumn(colName).getColumnId();

            if (!rowDesc.isKeyColumn(colId) && !F.eq(colName, rowDesc.type().affinityKey()))
                throw new IgniteSQLException("Invalid column name in KEYS clause of MERGE - it may include only " +
                    "key and/or affinity columns: " + colName, IgniteQueryErrorCode.PARSING);

            keys[i] = new GridSqlColumn(srcKeys[i], tbl, null, null, colName);
        }

        res.keys(keys);

        List<Expression[]> srcRows = MERGE_ROWS.get(merge);
        if (!srcRows.isEmpty()) {
            List<GridSqlElement[]> rows = new ArrayList<>(srcRows.size());
            for (Expression[] srcRow : srcRows) {
                GridSqlElement[] row = new GridSqlElement[srcRow.length];

                for (int i = 0; i < srcRow.length; i++)
                    row[i] = parseExpression(srcRow[i], false);

                rows.add(row);
            }
            res.rows(rows);
        }
        else {
            res.rows(Collections.<GridSqlElement[]>emptyList());
            res.query(parseQuery(MERGE_QUERY.get(merge)));
        }

        return res;
    }