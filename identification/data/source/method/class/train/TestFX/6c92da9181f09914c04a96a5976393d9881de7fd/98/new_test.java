    @Test
    public void hasVisiblePlaceholder() {
        assertThat(listView, ListViewMatchers.hasVisiblePlaceholder(new Label("Empty!")));
    }