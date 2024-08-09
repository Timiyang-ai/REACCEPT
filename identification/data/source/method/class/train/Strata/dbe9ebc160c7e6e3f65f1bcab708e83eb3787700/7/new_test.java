  @Test
  public void test_withCurrency() {
    RepoCurveZeroRateSensitivity base = RepoCurveZeroRateSensitivity.of(CURRENCY, YEARFRAC, GROUP, VALUE);
    RepoCurveZeroRateSensitivity test = base.withCurrency(GBP);
    assertThat(test.getRepoGroup()).isEqualTo(GROUP);
    assertThat(test.getCurveCurrency()).isEqualTo(CURRENCY);
    assertThat(test.getCurrency()).isEqualTo(GBP);
    assertThat(test.getYearFraction()).isEqualTo(YEARFRAC);
    assertThat(test.getSensitivity()).isEqualTo(VALUE);
  }