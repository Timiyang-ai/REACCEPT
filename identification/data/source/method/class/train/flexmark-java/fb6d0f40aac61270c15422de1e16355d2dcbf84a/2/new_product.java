public boolean isEmptyColumn(int column) {
        boolean[] result = new boolean[] { true };
        forAllContentRows(new TableRowManipulator() {
            @Override
            public int apply(
                    TableRow row,
                    int allRowsIndex,
                    ArrayList<TableRow> rows,
                    int index
            ) {
                if (!row.isEmptyColumn(column)) {
                    result[0] = false;
                    return BREAK;
                }
                return 0;
            }
        });

        return result[0];
    }