public DynamicRealmObject createObject(String className, Object primaryKeyValue) {
        Table table = schema.getTable(className);
        return new DynamicRealmObject(this,
                CheckedRow.getFromRow(OsObject.createWithPrimaryKey(sharedRealm, table, primaryKeyValue)));
    }