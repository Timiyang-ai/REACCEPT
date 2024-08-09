    @Test
    public void hasStrikethrough() {
        assertThat(foobarText, TextMatchers.hasStrikethrough(true));
    }