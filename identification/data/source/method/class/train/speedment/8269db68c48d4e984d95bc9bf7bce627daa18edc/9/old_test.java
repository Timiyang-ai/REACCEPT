    @Test
    void composeToBoolean() {
        final ToBoolean<String> toBoolean = string -> string.length() > 2;
        final ComposeToBoolean<String, String, ToBoolean<String>> composeToBoolean =
                (ComposeToBoolean<String, String, ToBoolean<String>>)
                        ComposedUtil.composeToBoolean(Function.identity(), toBoolean);

        assertNotNull(composeToBoolean.firstStep());
        assertNotNull(composeToBoolean.secondStep());

        assertFalse(composeToBoolean.applyAsBoolean(null));
        assertTrue(composeToBoolean.applyAsBoolean("test"));
        assertFalse(composeToBoolean.applyAsBoolean("a"));
    }