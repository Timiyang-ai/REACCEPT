@Test(groups={"tck"})
    public void test_minus_Period_positiveMonths() {
        MockSimplePeriod period = MockSimplePeriod.of(7, ChronoUnit.MONTHS);
        LocalDate t = TEST_2007_07_15.minus(period);
        assertEquals(t, LocalDate.of(2006, 12, 15));
    }