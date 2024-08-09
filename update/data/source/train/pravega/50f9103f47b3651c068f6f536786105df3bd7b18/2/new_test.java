@Test
    public void testCheckArgument() {
        AssertExtensions.assertThrows(
                "Unexpected behavior for checkArgument(arg, msg) with valid=false argument.",
                () -> Exceptions.checkArgument(false, "invalid-arg", "format msg %s", "foo"),
                ex -> ex instanceof IllegalArgumentException);

        // These should not throw.
        Exceptions.checkArgument(true, "valid-arg", "format msg %s", "foo");
    }