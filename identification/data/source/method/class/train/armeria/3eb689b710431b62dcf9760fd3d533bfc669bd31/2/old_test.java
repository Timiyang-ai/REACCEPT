    @Test
    void parse_null() {
        assertThatCode(() -> Scheme.parse(null)).isInstanceOf(NullPointerException.class);
    }