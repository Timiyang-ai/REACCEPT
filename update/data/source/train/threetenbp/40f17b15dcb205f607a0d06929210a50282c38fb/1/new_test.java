@Test(groups={"tck"})
    public void test_plus_PlusAdjuster() {
        MockSimplePeriod period = MockSimplePeriod.of(7, ChronoUnit.MONTHS);
        OffsetDate t = TEST_2007_07_15_PONE.plus(period);
        assertEquals(t, OffsetDate.of(LocalDate.of(2008, 2, 15), OFFSET_PONE));
    }