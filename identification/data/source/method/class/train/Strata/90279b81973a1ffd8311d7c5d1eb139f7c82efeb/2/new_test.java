  @Test
  public void test_discountFactor() {
    SimpleDiscountFactors test = SimpleDiscountFactors.of(GBP, DATE_VAL, CURVE);
    double relativeYearFraction = ACT_365F.relativeYearFraction(DATE_VAL, DATE_AFTER);
    double expected = CURVE.yValue(relativeYearFraction);
    assertThat(test.discountFactor(DATE_AFTER)).isEqualTo(expected);
  }