    @Test
    public void hasTableCell() {
        assertThat(tableView, TableViewMatchers.hasTableCell("alice"));
        assertThat(tableView, TableViewMatchers.hasTableCell("bob"));
    }