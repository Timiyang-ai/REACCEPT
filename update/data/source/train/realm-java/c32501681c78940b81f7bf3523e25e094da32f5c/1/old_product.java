public RealmResults<DynamicRealmObject> distinct(String className, String fieldName) {
        checkNotNullFieldName(fieldName);
        checkIfValid();
        Table table = schema.getTable(className);
        long columnIndex = table.getColumnIndex(fieldName);
        if (columnIndex == -1) {
            throw new IllegalArgumentException(String.format("Field name '%s' does not exist.", fieldName));
        }

        TableView tableView = table.getDistinctView(columnIndex);
        return RealmResults.createFromDynamicTableOrView(this, tableView, className);
    }