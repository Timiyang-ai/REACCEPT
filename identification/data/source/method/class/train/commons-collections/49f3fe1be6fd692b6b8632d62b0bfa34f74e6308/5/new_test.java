    @Test
    public void min() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        assertEquals(Integer.valueOf(1), ComparatorUtils.min(1, 10, null));
        assertEquals(Integer.valueOf(-10), ComparatorUtils.min(10, -10, null));

        assertEquals(Integer.valueOf(10), ComparatorUtils.min(1, 10, reversed));
        assertEquals(Integer.valueOf(10), ComparatorUtils.min(10, -10, reversed));

        try {
            ComparatorUtils.min(1, null, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            ComparatorUtils.min(null, 10, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }