@Test
    public void migratePrimaryKeyTableIfNeeded_primaryKeyTableNeedSearchIndex() {
        sharedRealm = SharedRealm.getInstance(config);
        sharedRealm.beginTransaction();
        OsObjectStore.setSchemaVersion(sharedRealm,0); // Create meta table
        Table table = sharedRealm.createTable(Table.getTableNameForClass("TestTable"));
        long column = table.addColumn(RealmFieldType.INTEGER, "PKColumn");
        table.addSearchIndex(column);
        OsObjectStore.setPrimaryKeyForObject(sharedRealm, "TestTable", "PKColumn");
        sharedRealm.commitTransaction();

        assertEquals("PKColumn", OsObjectStore.getPrimaryKeyForObject(sharedRealm, "TestTable"));
        // Now we have a pk table with search index.

        sharedRealm.beginTransaction();
        Table pkTable = sharedRealm.getTable("pk");
        long classColumn = pkTable.getColumnIndex("pk_table");
        pkTable.removeSearchIndex(classColumn);

        // Tries to add a pk for another table.
        Table table2 = sharedRealm.createTable(Table.getTableNameForClass("TestTable2"));
        long column2 = table2.addColumn(RealmFieldType.INTEGER, "PKColumn");
        table2.addSearchIndex(column2);
        try {
            OsObjectStore.setPrimaryKeyForObject(sharedRealm, "TestTable2", "PKColumn");
        } catch (IllegalStateException ignored) {
            // Column has no search index.
        }
        sharedRealm.commitTransaction();

        assertFalse(pkTable.hasSearchIndex(classColumn));

        Table.migratePrimaryKeyTableIfNeeded(sharedRealm);
        assertTrue(pkTable.hasSearchIndex(classColumn));

        sharedRealm.beginTransaction();
        // Now it works.
        table2.addSearchIndex(column2);
        OsObjectStore.setPrimaryKeyForObject(sharedRealm, "TestTable2", "PKColumn");
        sharedRealm.commitTransaction();
    }