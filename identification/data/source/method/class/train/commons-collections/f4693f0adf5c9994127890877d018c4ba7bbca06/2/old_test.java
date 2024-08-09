    @Test
    public void countMatches() {
        assertEquals(4, IterableUtils.countMatches(iterableB, EQUALS_TWO));
        assertEquals(0, IterableUtils.countMatches(null, EQUALS_TWO));

        try {
            assertEquals(0, IterableUtils.countMatches(iterableA, null));
            fail("predicate must not be null");
        } catch (final NullPointerException ex) {
            // expected
        }

        try {
            assertEquals(0, IterableUtils.countMatches(null, null));
            fail("predicate must not be null");
        } catch (final NullPointerException ex) {
            // expected
        }
    }