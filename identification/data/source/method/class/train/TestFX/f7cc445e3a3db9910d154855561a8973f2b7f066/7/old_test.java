    @Test
    public void hasText() {
        assertThat(fooMenuItem, MenuItemMatchers.hasText("foo"));
    }