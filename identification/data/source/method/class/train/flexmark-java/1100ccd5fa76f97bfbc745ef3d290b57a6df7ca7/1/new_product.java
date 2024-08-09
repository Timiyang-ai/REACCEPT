public TableCellOffsetInfo nextOffsetStop(Map<TableSectionType, Integer> stopPointsMap) {
        int stopOffset = getStopOffset(offset, table, stopPointsMap, true);
        if (stopOffset != -1) {
            return table.getCellOffsetInfo(stopOffset);
        }

        // go to after the table
        List<TableRow> allRows = table.getAllSectionRows();
        TableRow lastRow = allRows.get(allRows.size() - 1);
        TableCell lastCell = lastRow.cells.get(lastRow.cells.size() - 1);
        int offset = lastCell.getEndOffset();
        BasedSequence baseSequence = lastCell.text.getBaseSequence();

        int eolPos = baseSequence.endOfLineAnyEOL(offset);
        return table.getCellOffsetInfo(eolPos == -1 ? offset : eolPos + baseSequence.eolStartLength(eolPos));
    }