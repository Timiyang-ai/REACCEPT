private GridSqlUpdate parseUpdate(Update update) {
        GridSqlUpdate res = (GridSqlUpdate)h2ObjToGridObj.get(update);

        if (res != null)
            return res;

        res = new GridSqlUpdate();
        h2ObjToGridObj.put(update, res);

        GridSqlElement tbl = parseTableFilter(UPDATE_TARGET.get(update));

        List<Column> srcCols = UPDATE_COLUMNS.get(update);
        Map<Column, Expression> srcSet = UPDATE_SET.get(update);

        ArrayList<GridSqlColumn> cols = new ArrayList<>(srcCols.size());
        LinkedHashMap<String, GridSqlElement> set = new LinkedHashMap<>(srcSet.size());

        for (Column c : srcCols) {
            GridSqlColumn col = new GridSqlColumn(c, tbl, null, null, c.getName());
            col.resultType(fromColumn(c));
            cols.add(col);
            set.put(col.columnName(), parseExpression(srcSet.get(c), true));
        }

        GridSqlElement where = parseExpression(UPDATE_WHERE.get(update), true);
        GridSqlElement limit = parseExpression(UPDATE_LIMIT.get(update), true);

        res.target(tbl).cols(cols).set(set).where(where).limit(limit);
        return res;
    }