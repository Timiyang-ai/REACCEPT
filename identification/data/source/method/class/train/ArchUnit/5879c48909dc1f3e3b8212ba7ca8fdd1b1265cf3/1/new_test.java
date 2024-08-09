    @Test
    public void fullNameMatching_predicate() {
        assertThat(fullNameMatching(".*method\\(.*\\)"))
                .accepts(newHasNameAndFullName("method", "some.Foo.method(int)"))
                .accepts(newHasNameAndFullName("method", "some.Foo.method()"))
                .rejects(newHasNameAndFullName("method", "some.Foo.method"))
                .hasDescription("full name matching '.*method\\(.*\\)'");
    }