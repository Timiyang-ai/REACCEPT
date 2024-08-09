    @Test
    public void disjunction() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(4, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(6));
        assertTrue(set.contains(7));
        assertFalse(set.contains(3));
        assertFalse(set.contains(4));
        assertFalse(set.contains(5));

        final Set<Integer> set2 = SetUtils.disjunction(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA, set2);

        try {
            SetUtils.disjunction(setA, null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            SetUtils.disjunction(null, setA);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }