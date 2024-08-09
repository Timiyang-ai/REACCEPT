public DynamicRealmObject createObject(String className, Object primaryKeyValue) {
        Table table = schema.getTable(className);
        long index = table.addEmptyRowWithPrimaryKey(primaryKeyValue);
        return new DynamicRealmObject(this, table.getCheckedRow(index));
    }