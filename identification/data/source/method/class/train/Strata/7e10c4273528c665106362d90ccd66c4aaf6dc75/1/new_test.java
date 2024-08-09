  @Test
  public void test_withCurve() {
    IsdaCreditDiscountFactors test =
        IsdaCreditDiscountFactors.of(USD, VALUATION, CURVE).withCurve(CONST_CURVE);
    assertThat(test.getCurve()).isEqualTo(CONST_CURVE);
    assertThat(test.getDayCount()).isEqualTo(ACT_365L);
  }