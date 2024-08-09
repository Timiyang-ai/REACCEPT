    @Test
    public void max() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        assertEquals(Integer.valueOf(10), ComparatorUtils.max(1, 10, null));
        assertEquals(Integer.valueOf(10), ComparatorUtils.max(10, -10, null));

        assertEquals(Integer.valueOf(1), ComparatorUtils.max(1, 10, reversed));
        assertEquals(Integer.valueOf(-10), ComparatorUtils.max(10, -10, reversed));

        try {
            ComparatorUtils.max(1, null, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            ComparatorUtils.max(null, 10, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }