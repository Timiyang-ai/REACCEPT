    @Test
    void composeToDouble() {
        final ToDouble<String> toDouble = String::length;
        final ComposeToDouble<String, String, ToDouble<String>> composeToDouble =
                (ComposeToDouble<String, String, ToDouble<String>>)
                        ComposedUtil.composeToDouble(Function.identity(), toDouble);

        assertNotNull(composeToDouble.firstStep());
        assertNotNull(composeToDouble.secondStep());

        assertNull(composeToDouble.apply(null));
        assertNotNull(composeToDouble.apply("test"));
    }