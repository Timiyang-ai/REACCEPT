@Test
    public void testNewColumn() {
        NumberColumn column = (NumberColumn) TypeUtils.newColumn("test", NUMBER);
        assertThat(column, notNullValue());
    }