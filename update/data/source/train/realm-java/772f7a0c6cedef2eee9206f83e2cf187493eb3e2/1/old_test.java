@Test
    public void isNullable() {
        Table table = TestHelper.createTable(sharedRealm, "temp", new TestHelper.AdditionalTableSetup() {
            @Override
            public void execute(Table table) {
                table.addColumn(RealmFieldType.STRING, "string1", Table.NOT_NULLABLE);
                table.addColumn(RealmFieldType.STRING, "string2", Table.NULLABLE);
            }
        });

        assertFalse(table.isColumnNullable(0));
        assertTrue(table.isColumnNullable(1));
    }