    @Test
    public void union() {
        final SetView<Integer> set = SetUtils.union(setA, setB);
        assertEquals(7, set.size());
        assertTrue(set.containsAll(setA));
        assertTrue(set.containsAll(setB));

        final Set<Integer> set2 = SetUtils.union(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA, set2);

        try {
            SetUtils.union(setA, null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            SetUtils.union(null, setA);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }