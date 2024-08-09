@Test(groups={"tck"})
    public void test_toString_normal() {
        ZoneOffsetInfo test = new ZoneOffsetInfo(LocalDateTime.of(2010, 3, 31, 1, 0), OFFSET_0200, null);
        assertEquals(test.toString(), "OffsetInfo[2010-03-31T01:00 +02:00]");
    }