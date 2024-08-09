  @Test
  public void test_multipliedBy() {
    CreditCurveZeroRateSensitivity base = CreditCurveZeroRateSensitivity.of(LEGAL_ENTITY, GBP, YEAR_FRACTION, 32d);
    CreditCurveZeroRateSensitivity expected = CreditCurveZeroRateSensitivity.of(LEGAL_ENTITY, GBP, YEAR_FRACTION, 32d * 3.5d);
    CreditCurveZeroRateSensitivity test = base.multipliedBy(3.5d);
    assertThat(test).isEqualTo(expected);
  }