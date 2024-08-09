@Test
    public void testThrowIfIllegalArgument() {
        AssertExtensions.assertThrows(
                "Unexpected behavior for throwIfIllegalArgument(arg) with valid=false argument.",
                () -> Exceptions.throwIfIllegalArgument(false, "invalid-arg"),
                ex -> ex instanceof IllegalArgumentException);

        AssertExtensions.assertThrows(
                "Unexpected behavior for throwIfIllegalArgument(arg, msg) with valid=false argument.",
                () -> Exceptions.throwIfIllegalArgument(false, "invalid-arg", "format msg %s", "foo"),
                ex -> ex instanceof IllegalArgumentException);

        // These should not throw.
        Exceptions.throwIfIllegalArgument(true, "valid-arg");
        Exceptions.throwIfIllegalArgument(true, "valid-arg", "format msg %s", "foo");
    }