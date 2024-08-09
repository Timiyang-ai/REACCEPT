    @Test
    void joining() {
        assertNotNull(Expressions.joining(
            ToString.of(string -> (String) string),
            ToString.of(string -> (String) string)));
        assertNotNull(Expressions.joining(
            " ",
            ToString.of(string -> (String) string),
            ToString.of(string -> (String) string)));
        assertNotNull(Expressions.joining(
            " ",
            "prefix",
            "suffix",
            ToString.of(string -> (String) string),
            ToString.of(string -> (String) string)));

        assertNotNull(Expressions.joining(
            ToString.of(string -> (String) string),
            ToString.of(string -> (String) string),
            ToString.of(string -> (String) string)));
        assertNotNull(Expressions.joining(
            " ",
            ToString.of(string -> (String) string),
            ToString.of(string -> (String) string),
            ToString.of(string -> (String) string)));
        assertNotNull(Expressions.joining(
            " ",
            "prefix",
            "suffix",
            ToString.of(string -> (String) string),
            ToString.of(string -> (String) string),
            ToString.of(string -> (String) string)));
    }