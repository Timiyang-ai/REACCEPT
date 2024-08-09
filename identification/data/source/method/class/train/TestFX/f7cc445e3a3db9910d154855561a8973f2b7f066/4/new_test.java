    @Test
    public void isUnderlined() {
        assertThat(quuxText, TextMatchers.isUnderlined(true));
    }