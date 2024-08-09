@Test(groups={"tck"})
    public void test_plus_Period_ISOPeriod() {
        ISOPeriod period = ISOPeriod.of(7, MONTHS);
        ZonedDateTime t = ZonedDateTime.of(LocalDateTime.of(2008, 6, 1, 12, 30, 59, 500), ZONE_0100);
        ZonedDateTime expected = ZonedDateTime.of(LocalDateTime.of(2009, 1, 1, 12, 30, 59, 500), ZONE_0100);
        assertEquals(t.plus(period), expected);
    }