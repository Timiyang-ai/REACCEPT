@Test
    public void isNullable() {
        final AtomicLong columnKey0 = new AtomicLong(-1);
        final AtomicLong columnKey1 = new AtomicLong(-1);
        Table table = TestHelper.createTable(sharedRealm, "temp", new TestHelper.AdditionalTableSetup() {
            @Override
            public void execute(Table table) {
                columnKey0.set(table.addColumn(RealmFieldType.STRING, "string1", Table.NOT_NULLABLE));
                columnKey1.set(table.addColumn(RealmFieldType.STRING, "string2", Table.NULLABLE));
            }
        });

        assertFalse(table.isColumnNullable(columnKey0.get()));
        assertTrue(table.isColumnNullable(columnKey1.get()));
    }