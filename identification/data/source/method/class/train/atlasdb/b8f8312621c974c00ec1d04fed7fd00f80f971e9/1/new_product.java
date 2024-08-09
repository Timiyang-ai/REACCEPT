List<CellWithTimestamp> getTimestampsWithinRow(
            TableReference tableRef,
            byte[] row,
            byte[] startColumnInclusive,
            long startTimestampExclusive,
            int limit);