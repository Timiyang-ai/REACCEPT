@Test(groups={"tck"})
    public void test_atStartOfDay() {
        OffsetDate t = OffsetDate.of(2008, 6, 30, OFFSET_PTWO);
        assertEquals(t.atStartOfDay(ZONE_PARIS),
                ZonedDateTime.of(LocalDateTime.of(2008, 6, 30, 0, 0), ZONE_PARIS));
    }