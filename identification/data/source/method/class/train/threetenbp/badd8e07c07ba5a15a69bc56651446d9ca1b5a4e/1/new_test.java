@Test(groups={"tck"})
    public void test_with_adjustment() {
        final LocalDateTime sample = LocalDateTime.of(2012, 3, 4, 23, 5);
        TemporalAdjuster adjuster = new TemporalAdjuster() {
            @Override
            public Temporal doWithAdjustment(Temporal dateTime) {
                return sample;
            }
        };
        assertEquals(TEST_2007_07_15_12_30_40_987654321.with(adjuster), sample);
    }