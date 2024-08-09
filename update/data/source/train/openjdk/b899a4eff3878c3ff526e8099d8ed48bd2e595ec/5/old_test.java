@Test(groups={"tck"})
    public void test_toString_formatter() {
        DateTimeFormatter f = DateTimeFormatters.pattern("y M d H m s");
        String t = ZonedDateTime.of(dateTime(2010, 12, 3, 11, 30), ZONE_PARIS).toString(f);
        assertEquals(t, "2010 12 3 11 30 0");
    }