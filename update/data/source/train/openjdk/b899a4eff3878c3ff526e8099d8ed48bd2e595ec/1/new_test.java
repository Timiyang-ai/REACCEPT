@Test
    public void test_minus_TemporalAmount_positiveMonths() {
        MockSimplePeriod period = MockSimplePeriod.of(7, MONTHS);
        LocalDateTime t = TEST_2007_07_15_12_30_40_987654321.minus(period);
        assertEquals(t, LocalDateTime.of(2006, 12, 15, 12, 30, 40, 987654321));
    }