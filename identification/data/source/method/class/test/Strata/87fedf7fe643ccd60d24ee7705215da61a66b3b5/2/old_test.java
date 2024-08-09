  @Test
  public void test_zeroRatePointSensitivity() {
    SimpleDiscountFactors test = SimpleDiscountFactors.of(GBP, DATE_VAL, CURVE);
    double relativeYearFraction = ACT_365F.relativeYearFraction(DATE_VAL, DATE_AFTER);
    double df = CURVE.yValue(relativeYearFraction);
    ZeroRateSensitivity expected = ZeroRateSensitivity.of(GBP, relativeYearFraction, -df * relativeYearFraction);
    assertThat(test.zeroRatePointSensitivity(DATE_AFTER)).isEqualTo(expected);
  }