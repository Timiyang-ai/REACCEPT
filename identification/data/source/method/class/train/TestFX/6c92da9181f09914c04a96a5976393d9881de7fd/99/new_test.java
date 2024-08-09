    @Test
    public void hasPlaceholder() {
        assertThat(listView, ListViewMatchers.hasPlaceholder(new Label("Empty!")));
    }