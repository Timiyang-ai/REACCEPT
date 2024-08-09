    @Test
    public void hasText() {
        assertThat(foobarTextField, TextInputControlMatchers.hasText("foobar"));
    }