@Test(groups={"tck"})
    public void test_withZoneSameInstant() {
        ZonedDateTime base = ZonedDateTime.of(TEST_LOCAL_2008_06_30_11_30_59_500, ZONE_0100);
        ZonedDateTime test = base.withZoneSameInstant(ZONE_0200);
        ZonedDateTime expected = ZonedDateTime.of(TEST_LOCAL_2008_06_30_11_30_59_500.plusHours(1), ZONE_0200);
        assertEquals(test, expected);
    }