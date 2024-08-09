    @Test
    public void intersection() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(3, set.size());
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertTrue(set.contains(5));
        assertFalse(set.contains(1));
        assertFalse(set.contains(2));
        assertFalse(set.contains(6));
        assertFalse(set.contains(7));

        final Set<Integer> set2 = SetUtils.intersection(setA, SetUtils.<Integer>emptySet());
        assertEquals(SetUtils.<Integer>emptySet(), set2);

        try {
            SetUtils.intersection(setA, null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            SetUtils.intersection(null, setA);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }