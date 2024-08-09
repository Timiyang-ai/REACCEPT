    @Test
    public void hasText() {
        assertThat(foobarText, TextMatchers.hasText("foobar"));
    }