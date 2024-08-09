@Test
    public void testNewColumn() {
        DoubleColumn column = (DoubleColumn) TypeUtils.newColumn("test", DOUBLE);
        assertThat(column, notNullValue());
    }