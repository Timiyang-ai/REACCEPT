  @Test
  public void test_parameterSensitivity() {
    SimpleDiscountFactors test = SimpleDiscountFactors.of(GBP, DATE_VAL, CURVE);
    ZeroRateSensitivity point = ZeroRateSensitivity.of(GBP, 1d, 1d);
    assertThat(test.parameterSensitivity(point).size()).isEqualTo(1);
  }