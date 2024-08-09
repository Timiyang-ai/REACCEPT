@Test(groups={"tck"})
    public void test_toZoneId() {
        ZoneOffset offset = ZoneOffset.ofHoursMinutesSeconds(1, 2, 3);
        assertEquals(offset.toZoneId(), ZoneId.of(offset));
    }