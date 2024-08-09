    @Test
    public void hasText() {
        assertThat(foobarButton, LabeledMatchers.hasText("foobar"));
    }