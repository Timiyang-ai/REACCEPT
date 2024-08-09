@Test(groups={"tck"})
    public void test_truncatedTo_normal() {
        assertEquals(TEST_2008_6_30_11_30_59_000000500.truncatedTo(NANOS), TEST_2008_6_30_11_30_59_000000500);
        assertEquals(TEST_2008_6_30_11_30_59_000000500.truncatedTo(SECONDS), TEST_2008_6_30_11_30_59_000000500.withNano(0));
        assertEquals(TEST_2008_6_30_11_30_59_000000500.truncatedTo(DAYS), TEST_2008_6_30_11_30_59_000000500.with(LocalTime.MIDNIGHT));
    }