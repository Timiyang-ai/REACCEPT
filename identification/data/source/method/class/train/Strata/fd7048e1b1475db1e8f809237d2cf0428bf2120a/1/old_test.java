  @Test
  public void test_withCurve() {
    ZeroRateDiscountFactors test = ZeroRateDiscountFactors.of(GBP, DATE_VAL, CURVE).withCurve(CURVE2);
    assertThat(test.getCurve()).isEqualTo(CURVE2);
  }