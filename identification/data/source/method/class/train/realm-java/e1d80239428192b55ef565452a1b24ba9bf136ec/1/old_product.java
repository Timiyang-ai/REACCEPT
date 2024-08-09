public void remove(String className) {
        realm.checkNotInSync(); // Destructive modifications are not permitted.
        checkEmpty(className, EMPTY_STRING_MSG);
        String internalTableName = Table.getTableNameForClass(className);
        checkHasTable(className, "Cannot remove class because it is not in this Realm: " + className);
        Table table = getTable(className);
        if (table.hasPrimaryKey()) {
            table.setPrimaryKey(null);
        }
        realm.getSharedRealm().removeTable(internalTableName);
    }