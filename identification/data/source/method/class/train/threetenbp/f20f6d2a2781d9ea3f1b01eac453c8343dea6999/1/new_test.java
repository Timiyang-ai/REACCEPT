@Test(groups={"tck"})
    public void test_minus_PeriodProvider() {
        Period period = Period.of(7, LocalDateUnit.MONTHS);
        OffsetDate t = TEST_2007_07_15_PONE.minus(period);
        assertEquals(t, OffsetDate.of(2006, 12, 15, OFFSET_PONE));
    }