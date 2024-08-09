@Test(groups={"tck"})
    public void test_withMinute_normal() {
        ZonedDateTime base = ZonedDateTime.of(TEST_LOCAL_2008_06_30_11_30_59_500, ZONE_0100);
        ZonedDateTime test = base.withMinute(15);
        assertEquals(test, ZonedDateTime.of(TEST_LOCAL_2008_06_30_11_30_59_500.withMinute(15), ZONE_0100));
    }