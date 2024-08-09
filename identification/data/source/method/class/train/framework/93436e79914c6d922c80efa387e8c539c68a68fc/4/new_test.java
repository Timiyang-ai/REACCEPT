    @Test
    public void setColumnOrder_byColumn() {
        grid.setColumnOrder(randomColumn, lengthColumn);

        assertEquals(Arrays.asList(randomColumn, lengthColumn, fooColumn,
                objectColumn), grid.getColumns());
    }