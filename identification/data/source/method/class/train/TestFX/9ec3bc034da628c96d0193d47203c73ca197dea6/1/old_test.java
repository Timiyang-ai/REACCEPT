    @Test
    public void hasNumRows() {
        assertThat(tableView, TableViewMatchers.hasNumRows(4));
    }