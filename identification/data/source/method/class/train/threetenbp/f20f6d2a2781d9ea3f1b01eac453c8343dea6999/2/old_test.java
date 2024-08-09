@Test(groups={"tck"})
    public void test_plus_PeriodProvider() {
        PeriodProvider provider = Period.of(1, 2, 3, 4, 5, 6, 7);
        ZonedDateTime t = ZonedDateTime.of(LocalDateTime.of(2008, 6, 1, 12, 30, 59, 500), ZONE_0100);
        ZonedDateTime expected = ZonedDateTime.of(LocalDateTime.of(2009, 8, 4, 16, 36, 5, 507), ZONE_0100);
        assertEquals(t.plus(provider), expected);
    }