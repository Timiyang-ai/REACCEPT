@Test(groups={"tck"})
    public void test_withDateTime() {
        LocalDateTime ldt = LocalDateTime.of(2008, 6, 30, 23, 30, 59, 0);
        ZonedDateTime base = ZonedDateTime.of(ldt, ZONE_0100);
        LocalDateTime dt = LocalDateTime.of(2008, 6, 30, 11, 31, 0);
        ZonedDateTime test = base.withDateTime(dt);
        assertEquals(test.toLocalDateTime(), dt);
    }