public RealmResults<DynamicRealmObject> distinct(String className, String fieldName) {
        checkIfValid();
        Table table = schema.getTable(className);
        long columnIndex = RealmQuery.getAndValidateDistinctColumnIndex(fieldName, table);
        TableView tableView = table.getDistinctView(columnIndex);
        return RealmResults.createFromDynamicTableOrView(this, tableView, className);
    }