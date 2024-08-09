  @Test
  public void test_periodRate() {
    DiscountOvernightIndexRates test = DiscountOvernightIndexRates.of(EUR_EONIA, DFCURVE, SERIES);
    double accrualFactor = EUR_EONIA.getDayCount().yearFraction(DATE_AFTER, DATE_AFTER_END);
    double expected = (DFCURVE.discountFactor(DATE_AFTER) / DFCURVE.discountFactor(DATE_AFTER_END) - 1) / accrualFactor;
    assertThat(test.periodRate(EUR_EONIA_AFTER, DATE_AFTER_END)).isCloseTo(expected, offset(1e-8));
  }