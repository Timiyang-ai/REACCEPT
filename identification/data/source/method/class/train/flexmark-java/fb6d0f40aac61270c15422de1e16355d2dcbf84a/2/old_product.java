public boolean isEmptyColumn(final int column) {
        final boolean[] result = new boolean[] { true };
        forAllContentRows(new TableRowManipulator() {
            @Override
            public int apply(
                    final TableRow row,
                    final int allRowsIndex,
                    final ArrayList<TableRow> rows,
                    final int index
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