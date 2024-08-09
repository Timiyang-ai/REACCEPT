@Test(groups={"tck"})
    public void test_withEarlierOffsetAtOverlap_atOverlap() {
        OffsetDateTime odt = OffsetDateTime.of(TEST_PARIS_OVERLAP_2008_10_26_02_30, OFFSET_0100);
        ZonedDateTime base = ZonedDateTime.of(odt, ZONE_PARIS);
        ZonedDateTime test = base.withEarlierOffsetAtOverlap();
        assertEquals(test.getOffset(), OFFSET_0200);  // offset changed to earlier
        assertEquals(test.getDateTime(), base.getDateTime());  // date-time not changed
    }