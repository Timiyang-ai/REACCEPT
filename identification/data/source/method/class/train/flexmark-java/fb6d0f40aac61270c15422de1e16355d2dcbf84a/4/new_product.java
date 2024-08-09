public boolean isEmptyColumn(int column) {
        boolean[] result = new boolean[] { true };
        forAllContentRows((row, allRowsIndex, rows, index) -> {
            if (!row.isEmptyColumn(column)) {
                result[0] = false;
                return TableRowManipulator.BREAK;
            }
            return 0;
        });

        return result[0];
    }