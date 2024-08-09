@Test(groups={"tck"})
    public void test_minus_Period() {
        SimplePeriod period = SimplePeriod.of(7, LocalPeriodUnit.MONTHS);
        ZonedDateTime t = ZonedDateTime.of(LocalDateTime.of(2008, 6, 1, 12, 30, 59, 500), ZONE_0100);
        ZonedDateTime expected = ZonedDateTime.of(LocalDateTime.of(2007, 11, 1, 12, 30, 59, 500), ZONE_0100);
        assertEquals(t.minus(period), expected);
    }