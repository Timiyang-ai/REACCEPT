    @Test
    public void hasListCell() {
        assertThat(listView, ListViewMatchers.hasListCell("alice"));
    }