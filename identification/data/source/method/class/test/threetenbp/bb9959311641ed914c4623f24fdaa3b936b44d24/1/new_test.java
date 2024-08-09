@Test(groups={"tck"})
    public void test_with_adjustment() {
        final OffsetDateTime sample = OffsetDateTime.of(2012, 3, 4, 23, 5, OFFSET_PONE);
        DateTimeAdjuster adjuster = new DateTimeAdjuster() {
            @Override
            public AdjustableDateTime doAdjustment(AdjustableDateTime calendrical) {
                return sample;
            }
        };
        assertEquals(TEST_2008_6_30_11_30_59_000000500.with(adjuster), sample);
    }