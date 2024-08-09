  @Test
  public void test_zeroRatePointSensitivity() {
    IsdaCreditDiscountFactors test = IsdaCreditDiscountFactors.of(USD, VALUATION, CURVE);
    double relativeYearFraction = ACT_365F.relativeYearFraction(VALUATION, DATE_AFTER);
    double df = Math.exp(-relativeYearFraction * CURVE.yValue(relativeYearFraction));
    ZeroRateSensitivity expected = ZeroRateSensitivity.of(USD, relativeYearFraction, -df * relativeYearFraction);
    assertThat(test.zeroRatePointSensitivity(DATE_AFTER)).isEqualTo(expected);
  }