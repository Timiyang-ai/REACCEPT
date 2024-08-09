public boolean isEmptyColumn(int column) {
        for (TableRow row : getAllRows(false)) {
            if (!row.isEmptyColumn(column)) {
                return false;
            }
        }
        return true;
    }