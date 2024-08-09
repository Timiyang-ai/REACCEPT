    @Test
    public void chainedComparator() {
        // simple test: chain 2 natural comparators
        final Comparator<Integer> comp = ComparatorUtils.chainedComparator(ComparatorUtils.<Integer>naturalComparator(),
                                                                     ComparatorUtils.<Integer>naturalComparator());
        assertTrue(comp.compare(1, 2) < 0);
        assertTrue(comp.compare(1, 1) == 0);
        assertTrue(comp.compare(2, 1) > 0);
    }