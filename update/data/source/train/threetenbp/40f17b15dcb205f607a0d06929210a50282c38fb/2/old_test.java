@Test(groups={"tck"})
    public void test_minus_MinusAdjuster() {
        MockSimplePeriod period = MockSimplePeriod.of(7, ChronoUnit.MINUTES);
        OffsetTime t = TEST_11_30_59_500_PONE.minus(period);
        assertEquals(t, OffsetTime.of(11, 23, 59, 500, OFFSET_PONE));
    }