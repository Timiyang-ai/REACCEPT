  @Test
  public void test_multipliedBy() {
    RepoCurveZeroRateSensitivity base = RepoCurveZeroRateSensitivity.of(CURRENCY, YEARFRAC, GROUP, VALUE);
    double rate = 2.4d;
    RepoCurveZeroRateSensitivity test = base.multipliedBy(rate);
    RepoCurveZeroRateSensitivity expected = RepoCurveZeroRateSensitivity.of(CURRENCY, YEARFRAC, GROUP, VALUE * rate);
    assertThat(test).isEqualTo(expected);
  }