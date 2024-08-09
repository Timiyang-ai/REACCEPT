@Test(groups={"tck"})
    public void test_withLaterOffsetAtOverlap_notAtOverlap() {
        OffsetDateTime odt = OffsetDateTime.of(TEST_LOCAL_2008_06_30_11_30_59_500, OFFSET_0200);
        ZonedDateTime base = ZonedDateTime.of(odt, ZONE_PARIS);
        ZonedDateTime test = base.withLaterOffsetAtOverlap();
        assertEquals(test, base);  // not changed
    }