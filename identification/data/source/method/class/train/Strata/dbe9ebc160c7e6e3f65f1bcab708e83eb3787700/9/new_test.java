  @Test
  public void test_createZeroRateSensitivity() {
    IssuerCurveZeroRateSensitivity base = IssuerCurveZeroRateSensitivity.of(CURRENCY, YEARFRAC, GBP, GROUP, VALUE);
    ZeroRateSensitivity expected = ZeroRateSensitivity.of(CURRENCY, YEARFRAC, GBP, VALUE);
    ZeroRateSensitivity test = base.createZeroRateSensitivity();
    assertThat(test).isEqualTo(expected);
  }