  @Test
  public void test_multipliedBy() {
    IssuerCurveZeroRateSensitivity base = IssuerCurveZeroRateSensitivity.of(CURRENCY, YEARFRAC, GROUP, VALUE);
    double rate = 2.4d;
    IssuerCurveZeroRateSensitivity test = base.multipliedBy(rate);
    IssuerCurveZeroRateSensitivity expected = IssuerCurveZeroRateSensitivity.of(CURRENCY, YEARFRAC, GROUP, VALUE * rate);
    assertThat(test).isEqualTo(expected);
  }