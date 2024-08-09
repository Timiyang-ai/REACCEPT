public boolean isEmptyColumn(int column) {
        for (TableRow row : allRows(false)) {
            if (!row.isEmptyColumn(column)) {
                return false;
            }
        }
        return true;
    }