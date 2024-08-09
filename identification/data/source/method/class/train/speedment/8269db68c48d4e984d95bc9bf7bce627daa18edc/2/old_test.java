    @Test
    void composeToFloat() {
        final ToFloat<String> toFloat = String::length;
        final ComposeToFloat<String, String, ToFloat<String>> composeToFloat =
                (ComposeToFloat<String, String, ToFloat<String>>)
                        ComposedUtil.composeToFloat(Function.identity(), toFloat);

        assertNotNull(composeToFloat.firstStep());
        assertNotNull(composeToFloat.secondStep());

        assertNull(composeToFloat.apply(null));
        assertNotNull(composeToFloat.apply("test"));
    }