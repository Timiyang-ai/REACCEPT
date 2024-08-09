@Test
    public void testThrowIfIllegalArrayRange() {
        // Run a range of fixed size over an interval and verify conditions.

        int minBound = 10;
        int maxBound = 20;
        int length = 5;
        for (int i = minBound - length - 1; i <= maxBound + 1; i++) {
            boolean valid = i >= minBound && i + length <= maxBound;
            if (valid) {
                Exceptions.throwIfIllegalArrayRange(i, length, minBound, maxBound, "start", "length");
            }
            else {
                final int index = i;
                AssertExtensions.assertThrows(
                        String.format("Unexpected behavior for throwIfIllegalArrayRange(index = %d, length = %d, minbound = %d, maxbound = %d).", index, length, minBound, maxBound),
                        () -> Exceptions.throwIfIllegalArrayRange(index, length, minBound, maxBound, "start", "length"),
                        ex -> ex instanceof ArrayIndexOutOfBoundsException);
            }
        }

        // Negative length.
        AssertExtensions.assertThrows(
                "Unexpected behavior for throwIfIllegalArrayRange() with negative length.",
                () -> Exceptions.throwIfIllegalArrayRange(10, -1, 8, 20, "start", "length"),
                ex -> ex instanceof IllegalArgumentException);

        // Empty array with empty range (this is a valid case).
        Exceptions.throwIfIllegalArrayRange(0, 0, 0, 0, "start", "length");

        // Empty array with non-empty range (not a valid case).
        AssertExtensions.assertThrows(
                "Unexpected behavior for throwIfIllegalArrayRange() with non-empty range in an empty array.",
                () -> Exceptions.throwIfIllegalArrayRange(0, 1, 0, 0, "start", "length"),
                ex -> ex instanceof ArrayIndexOutOfBoundsException);
    }