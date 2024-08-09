@Test(groups={"tck"})
    public void test_with_TimeAdjuster_resolver() {
        LocalDateTime ldt = LocalDateTime.of(2008, 6, 30, 23, 30, 59, 0);
        ZonedDateTime base = ZonedDateTime.of(ldt, ZONE_0100);
        ZonedDateTime test = base.with(new TimeAdjuster() {
            public LocalTime adjustTime(LocalTime time) {
                return time.withHour(1);
            }
        }, ZoneResolvers.retainOffset());
        assertEquals(test, ZonedDateTime.of(ldt.withHour(1), ZONE_0100));
    }