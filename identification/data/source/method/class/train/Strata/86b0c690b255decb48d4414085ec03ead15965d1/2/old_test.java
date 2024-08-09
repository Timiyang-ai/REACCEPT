  @Test
  public void test_rate_beforeValuation_fixing() {
    DiscountOvernightIndexRates test = DiscountOvernightIndexRates.of(EUR_EONIA, DFCURVE, SERIES);
    assertThat(test.rate(EUR_EONIA_BEFORE)).isEqualTo(RATE_BEFORE);
  }