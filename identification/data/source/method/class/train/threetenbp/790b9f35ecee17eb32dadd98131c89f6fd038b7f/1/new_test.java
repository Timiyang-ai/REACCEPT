@Test(groups={"tck"})
    public void test_plus_Period_positiveHours() {
        Period period = Period.of(7, LocalPeriodUnit.HOURS);
        LocalTime t = TEST_12_30_40_987654321.plus(period);
        assertEquals(t, LocalTime.of(19, 30, 40, 987654321));
    }