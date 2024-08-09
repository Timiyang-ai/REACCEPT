    @Test
    public void hasSelectedRow() {
        listView.getSelectionModel().select(0);
        assertThat(listView, ListViewMatchers.hasSelectedRow("alice"));
    }