public List<CellWithTimestamp> getTimestampsWithinRow(
            TableReference tableRef,
            byte[] row,
            byte[] startColumnInclusive,
            long startTimestampExclusive,
            int limit) {
        long invertedTimestamp = ~startTimestampExclusive;
        CqlQuery query = new CqlQuery(
                "SELECT column1, column2 FROM %s WHERE key = %s AND (column1, column2) > (%s, %s) LIMIT %s;",
                quotedTableName(tableRef),
                key(row),
                column1(startColumnInclusive),
                column2(invertedTimestamp),
                limit(limit));
        return query.executeAndGetCells(row, result -> getCellFromKeylessRow(result, row));
    }