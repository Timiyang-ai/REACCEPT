  @Test
  public void test_discountFactor() {
    ZeroRatePeriodicDiscountFactors test = ZeroRatePeriodicDiscountFactors.of(GBP, DATE_VAL, CURVE);
    double relativeYearFraction = ACT_365F.relativeYearFraction(DATE_VAL, DATE_AFTER);
    double expected = Math.pow(1.0d + CURVE.yValue(relativeYearFraction) / CMP_PERIOD,
        -CMP_PERIOD * relativeYearFraction);
    assertThat(test.discountFactor(DATE_AFTER)).isEqualTo(expected);
  }