    @Test
    public void create() {
        String actual = Url.create(Arrays.asList("path", "to", "somewhere"));
        String expected = "http://localhost:9999/path/to/somewhere";

        assertThat(actual).isEqualTo(expected);
    }