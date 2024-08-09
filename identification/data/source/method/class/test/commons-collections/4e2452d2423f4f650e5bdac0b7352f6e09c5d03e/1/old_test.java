    @Test
    public void testpredicatedSet() {
        final Predicate<Object> predicate = o -> o instanceof String;
        final Set<Object> set = SetUtils.predicatedSet(new HashSet<>(), predicate);
        assertTrue("returned object should be a PredicatedSet", set instanceof PredicatedSet);
        try {
            SetUtils.predicatedSet(new HashSet<>(), null);
            fail("Expecting NullPointerException for null predicate.");
        } catch (final NullPointerException ex) {
            // expected
        }
        try {
            SetUtils.predicatedSet(null, predicate);
            fail("Expecting NullPointerException for null set.");
        } catch (final NullPointerException ex) {
            // expected
        }
    }