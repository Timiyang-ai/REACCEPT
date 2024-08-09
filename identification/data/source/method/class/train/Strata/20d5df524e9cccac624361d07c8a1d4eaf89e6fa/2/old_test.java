  @Test
  public void test_zeroRatePointSensitivity() {
    RepoCurveDiscountFactors base = RepoCurveDiscountFactors.of(DSC_FACTORS, GROUP);
    RepoCurveZeroRateSensitivity expected =
        RepoCurveZeroRateSensitivity.of(DSC_FACTORS.zeroRatePointSensitivity(DATE_AFTER), GROUP);
    RepoCurveZeroRateSensitivity computed = base.zeroRatePointSensitivity(DATE_AFTER);
    assertThat(computed).isEqualTo(expected);
  }