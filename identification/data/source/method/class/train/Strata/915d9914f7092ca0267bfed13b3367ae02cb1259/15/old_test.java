  @Test
  public void test_discountFactor() {
    ZeroRateDiscountFactors test = ZeroRateDiscountFactors.of(GBP, DATE_VAL, CURVE);
    double relativeYearFraction = ACT_365F.relativeYearFraction(DATE_VAL, DATE_AFTER);
    double expected = Math.exp(-relativeYearFraction * CURVE.yValue(relativeYearFraction));
    assertThat(test.discountFactor(DATE_AFTER)).isEqualTo(expected);
  }