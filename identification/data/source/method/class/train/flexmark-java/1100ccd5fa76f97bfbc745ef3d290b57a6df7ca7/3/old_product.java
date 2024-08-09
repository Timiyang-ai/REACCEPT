public TableCellOffsetInfo previousOffsetStop(final Map<TableSectionType, Integer> stopPointsMap) {
        int stopOffset = getStopOffset(offset, table, stopPointsMap, false);
        if (stopOffset != -1) {
            return table.getCellOffsetInfo(stopOffset);
        }
        return table.getCellOffsetInfo(table.getTableStartOffset());
    }