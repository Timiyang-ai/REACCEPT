@Test(groups={"tck"})
    public void test_minus_Period_positiveHours() {
        Period period = Period.of(7, LocalPeriodUnit.HOURS);
        LocalTime t = TEST_12_30_40_987654321.minus(period);
        assertEquals(t, LocalTime.of(5, 30, 40, 987654321));
    }