@Test
    public void testThrowIfClosed() {
        AssertExtensions.assertThrows(
                "Unexpected behavior for throwIfClosed() with closed=true argument.",
                () -> Exceptions.throwIfClosed(true, "object"),
                ex -> ex instanceof ObjectClosedException);

        // These should not throw.
        Exceptions.throwIfClosed(false, "object");
    }