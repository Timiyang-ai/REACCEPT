@Test(groups={"tck"})
    public void test_plus_Adjuster_positiveHours() {
        PlusAdjuster period = MockSimplePeriod.of(7, ChronoUnit.HOURS);
        LocalTime t = TEST_12_30_40_987654321.plus(period);
        assertEquals(t, LocalTime.of(19, 30, 40, 987654321));
    }