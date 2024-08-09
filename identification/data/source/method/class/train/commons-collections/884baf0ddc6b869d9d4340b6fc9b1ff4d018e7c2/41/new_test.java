    @Test
    public void booleanComparator() {
        Comparator<Boolean> comp = ComparatorUtils.booleanComparator(true);
        assertTrue(comp.compare(Boolean.TRUE, Boolean.FALSE) < 0);
        assertTrue(comp.compare(Boolean.TRUE, Boolean.TRUE) == 0);
        assertTrue(comp.compare(Boolean.FALSE, Boolean.TRUE) > 0);

        comp = ComparatorUtils.booleanComparator(false);
        assertTrue(comp.compare(Boolean.TRUE, Boolean.FALSE) > 0);
        assertTrue(comp.compare(Boolean.TRUE, Boolean.TRUE) == 0);
        assertTrue(comp.compare(Boolean.FALSE, Boolean.TRUE) < 0);
    }