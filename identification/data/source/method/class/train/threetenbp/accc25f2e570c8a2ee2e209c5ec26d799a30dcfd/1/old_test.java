@Test(groups={"tck"})
    public void test_getID() {
        ZoneOffset offset = ZoneOffset.ofHoursMinutesSeconds(1, 0, 0);
        assertEquals(offset.getId(), "+01:00");
        offset = ZoneOffset.ofHoursMinutesSeconds(1, 2, 3);
        assertEquals(offset.getId(), "+01:02:03");
        offset = ZoneOffset.UTC;
        assertEquals(offset.getId(), "Z");
    }