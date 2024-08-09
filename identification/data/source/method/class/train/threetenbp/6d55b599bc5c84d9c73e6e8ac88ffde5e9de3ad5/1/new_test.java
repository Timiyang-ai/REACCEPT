@Test(groups={"tck"})
    public void test_withZoneLocked() {
        ZonedDateTime base = ZonedDateTime.of(TEST_LOCAL_2008_06_30_11_30_59_500, ZONE_PARIS);
        ZonedDateTime test = base.withLockedOffset();
        ZonedDateTime expected = ZonedDateTime.of(TEST_LOCAL_2008_06_30_11_30_59_500, ZONE_0200);
        assertEquals(test, expected);
    }