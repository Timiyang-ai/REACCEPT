    @Test
    void composeToString() {
        final ToString<String> toString = string -> string;
        final ComposeToString<String, String, ToString<String>> composeToString =
                (ComposeToString<String, String, ToString<String>>)
                        ComposedUtil.composeToString(Function.identity(), toString);

        assertNotNull(composeToString.firstStep());
        assertNotNull(composeToString.secondStep());

        assertNull(composeToString.apply(null));
        assertNotNull(composeToString.apply("test"));
    }