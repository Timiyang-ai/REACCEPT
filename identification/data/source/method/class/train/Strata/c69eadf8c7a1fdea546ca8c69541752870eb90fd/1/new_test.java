  @Test
  public void test_parameterSensitivity() {
    DiscountOvernightIndexRates test = DiscountOvernightIndexRates.of(EUR_EONIA, DFCURVE, SERIES);
    OvernightRateSensitivity point = OvernightRateSensitivity.ofPeriod(EUR_EONIA_AFTER, DATE_AFTER_END, EUR, 1d);
    assertThat(test.parameterSensitivity(point).size()).isEqualTo(1);
  }