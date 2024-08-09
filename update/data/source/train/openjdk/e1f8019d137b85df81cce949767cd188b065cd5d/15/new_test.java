@Test
    public void test_plusYears() {
        assertPeriod(Period.of(1, 2, 3).plusYears(0), 1, 2, 3);
        assertPeriod(Period.of(1, 2, 3).plusYears(10), 11, 2, 3);
        assertPeriod(Period.of(1, 2, 3).plusYears(-10), -9, 2, 3);
        assertPeriod(Period.of(1, 2, 3).plusYears(-1), 0, 2, 3);

        assertPeriod(Period.of(1, 2, 3).plus(Period.ofYears(0)), 1, 2, 3);
        assertPeriod(Period.of(1, 2, 3).plus(Period.ofYears(10)), 11, 2, 3);
        assertPeriod(Period.of(1, 2, 3).plus(Period.ofYears(-10)), -9, 2, 3);
        assertPeriod(Period.of(1, 2, 3).plus(Period.ofYears(-1)), 0, 2, 3);
    }