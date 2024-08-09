    @Test
    void composeToShort() {
        final ToShort<String> toShort = string -> (short) string.length();
        final ComposeToShort<String, String, ToShort<String>> composeToShort =
                (ComposeToShort<String, String, ToShort<String>>)
                        ComposedUtil.composeToShort(Function.identity(), toShort);

        assertNotNull(composeToShort.firstStep());
        assertNotNull(composeToShort.secondStep());

        assertNull(composeToShort.apply(null));
        assertNotNull(composeToShort.apply("test"));
    }