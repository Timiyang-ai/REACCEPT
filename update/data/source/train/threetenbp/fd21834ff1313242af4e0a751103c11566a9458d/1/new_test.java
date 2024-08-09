@Test(groups={"tck"})
    public void test_with_adjustment() {
        final OffsetDateTime sample = OffsetDateTime.of(2012, 3, 4, 23, 5, OFFSET_PONE);
        TemporalAdjuster adjuster = new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal dateTime) {
                return sample;
            }
        };
        assertEquals(TEST_2008_6_30_11_30_59_000000500.with(adjuster), sample);
    }