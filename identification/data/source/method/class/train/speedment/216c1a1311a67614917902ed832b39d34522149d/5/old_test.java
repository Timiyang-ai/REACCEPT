    @Test
    void requireNonPositive() {
        assertEquals(-1.0, DoubleRangeUtil.requireNonPositive(-1.0));
    }