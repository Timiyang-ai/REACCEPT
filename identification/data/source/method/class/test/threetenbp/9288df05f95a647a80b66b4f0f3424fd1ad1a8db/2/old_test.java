@Test(groups={"tck"})
    public void test_with_adjustment() {
        final OffsetTime sample = OffsetTime.of(23, 5, OFFSET_PONE);
        WithAdjuster adjuster = new WithAdjuster() {
            @Override
            public DateTime doWithAdjustment(DateTime dateTime) {
                return sample;
            }
        };
        assertEquals(TEST_11_30_59_500_PONE.with(adjuster), sample);
    }