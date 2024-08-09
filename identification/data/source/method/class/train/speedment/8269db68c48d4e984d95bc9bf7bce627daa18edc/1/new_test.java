    @Test
    void composeToChar() {
        final ToChar<String> toChar = string -> string.toCharArray()[0];
        final ComposeToChar<String, String, ToChar<String>> composeToChar =
                (ComposeToChar<String, String, ToChar<String>>)
                        ComposedUtil.composeToChar(Function.identity(), toChar);

        assertNotNull(composeToChar.firstStep());
        assertNotNull(composeToChar.secondStep());

        assertEquals('t', composeToChar.applyAsChar("test"));

        assertNull(composeToChar.apply(null));
        assertNotNull(composeToChar.apply("test"));
    }