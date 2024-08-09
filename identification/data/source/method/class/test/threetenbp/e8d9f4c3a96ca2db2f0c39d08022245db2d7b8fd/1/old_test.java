@Test(groups={"tck"})
    public void test_withLaterOffsetAtOverlap() {
        OffsetDateTime odt = OffsetDateTime.of(2008, 10, 26, 2, 30, OFFSET_0200);
        ZonedDateTime base = ZonedDateTime.of(odt, ZONE_PARIS);
        ZonedDateTime test = base.withLaterOffsetAtOverlap();
        assertEquals(test.getOffset(), OFFSET_0100);  // offset changed to later
    }