  @Test
  public void test_periodRatePointSensitivity() {
    DiscountOvernightIndexRates test = DiscountOvernightIndexRates.of(EUR_EONIA, DFCURVE, SERIES);
    OvernightRateSensitivity expected = OvernightRateSensitivity.ofPeriod(EUR_EONIA_AFTER, DATE_AFTER_END, EUR, 1d);
    assertThat(test.periodRatePointSensitivity(EUR_EONIA_AFTER, DATE_AFTER_END)).isEqualTo(expected);
  }