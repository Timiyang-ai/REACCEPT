    @Test
    public void nullLowComparator() {
        final Comparator<Integer> comp = ComparatorUtils.nullLowComparator(null);
        assertTrue(comp.compare(null, 10) < 0);
        assertTrue(comp.compare(null, null) == 0);
        assertTrue(comp.compare(10, null) > 0);
    }