public boolean isNullable(String fieldName) {
        long columnIndex = getColumnKey(fieldName);
        return table.isColumnNullable(columnIndex);
    }