@Test(groups={"tck"})
    public void test_with_adjustment() {
        final OffsetDate sample = OffsetDate.of(LocalDate.of(2012, 3, 4), OFFSET_PONE);
        TemporalAdjuster adjuster = new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal dateTime) {
                return sample;
            }
        };
        assertEquals(TEST_2007_07_15_PONE.with(adjuster), sample);
    }