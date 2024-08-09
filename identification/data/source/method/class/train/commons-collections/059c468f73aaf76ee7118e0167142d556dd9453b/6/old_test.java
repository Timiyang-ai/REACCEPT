    @Test
    public void difference() {
        final SetView<Integer> set = SetUtils.difference(setA, setB);
        assertEquals(2, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        for (final Integer i : setB) {
            assertFalse(set.contains(i));
        }

        final Set<Integer> set2 = SetUtils.difference(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA, set2);

        try {
            SetUtils.difference(setA, null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            SetUtils.difference(null, setA);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }