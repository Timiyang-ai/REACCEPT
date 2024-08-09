@Test(groups={"tck"})
    public void test_with_adjustment() {
        final LocalTime sample = LocalTime.of(23, 5);
        WithAdjuster adjuster = new WithAdjuster() {
            @Override
            public DateTime doAdjustment(DateTime calendrical) {
                return sample;
            }
        };
        assertEquals(TEST_12_30_40_987654321.with(adjuster), sample);
    }