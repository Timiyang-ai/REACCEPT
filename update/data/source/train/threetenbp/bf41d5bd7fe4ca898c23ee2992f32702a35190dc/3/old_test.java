@Test(groups={"tck"})
    public void test_withMinuteOfHour_normal() {
        LocalDateTime ldt = LocalDateTime.of(2008, 6, 30, 23, 30, 59, 0);
        ZonedDateTime base = ZonedDateTime.of(ldt, ZONE_0100);
        ZonedDateTime test = base.withMinuteOfHour(15);
        assertEquals(test, ZonedDateTime.of(ldt.withMinuteOfHour(15), ZONE_0100));
    }