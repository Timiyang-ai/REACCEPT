@Test(groups={"tck"})
    public void test_minus_PeriodProvider() {
        PeriodProvider provider = Period.ofDateFields(1, 2, 3);
        OffsetDate t = TEST_2007_07_15_PONE.minus(provider);
        assertEquals(t, OffsetDate.of(2006, 5, 12, OFFSET_PONE));
    }