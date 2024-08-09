@Test
    public void testCheckNotClosed() {
        AssertExtensions.assertThrows(
                "Unexpected behavior for checkNotClosed() with closed=true argument.",
                () -> Exceptions.checkNotClosed(true, "object"),
                ex -> ex instanceof ObjectClosedException);

        // These should not throw.
        Exceptions.checkNotClosed(false, "object");
    }