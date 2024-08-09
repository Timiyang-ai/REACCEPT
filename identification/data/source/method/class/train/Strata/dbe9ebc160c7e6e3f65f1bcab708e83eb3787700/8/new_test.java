  @Test
  public void test_withCurrency() {
    IssuerCurveZeroRateSensitivity base = IssuerCurveZeroRateSensitivity.of(CURRENCY, YEARFRAC, GROUP, VALUE);
    IssuerCurveZeroRateSensitivity test = base.withCurrency(GBP);
    assertThat(test.getLegalEntityGroup()).isEqualTo(GROUP);
    assertThat(test.getCurveCurrency()).isEqualTo(CURRENCY);
    assertThat(test.getCurrency()).isEqualTo(GBP);
    assertThat(test.getYearFraction()).isEqualTo(YEARFRAC);
    assertThat(test.getSensitivity()).isEqualTo(VALUE);
  }