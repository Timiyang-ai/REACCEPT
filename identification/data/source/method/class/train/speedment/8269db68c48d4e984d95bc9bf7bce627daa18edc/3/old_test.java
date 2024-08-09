    @Test
    void composeToInt() {
        final ToInt<String> toInt = String::length;
        final ComposeToInt<String, String, ToInt<String>> composeToInt =
                (ComposeToInt<String, String, ToInt<String>>)
                        ComposedUtil.composeToInt(Function.identity(), toInt);

        assertNotNull(composeToInt.firstStep());
        assertNotNull(composeToInt.secondStep());

        assertNull(composeToInt.apply(null));
        assertNotNull(composeToInt.apply("test"));
    }