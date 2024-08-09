    @Test
    public void getTimestampsWithinRow() {
        String expected = "SELECT column1, column2 FROM \"foo__bar\" WHERE key = 0x0102"
                + " AND (column1, column2) > (0x0304, -124) LIMIT 100;";

        executor.getTimestampsWithinRow(TABLE_REF, ROW, COLUMN, TIMESTAMP, LIMIT);

        verify(queryExecutor).execute(argThat(cqlQueryMatcher(expected)), eq(ROW));
    }