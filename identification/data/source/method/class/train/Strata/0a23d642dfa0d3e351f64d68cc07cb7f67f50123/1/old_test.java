  @Test
  public void test_getCurves() {
    ImmutableRatesProvider test = ImmutableRatesProvider.builder(VAL_DATE)
        .discountCurve(GBP, DISCOUNT_CURVE_GBP)
        .discountCurve(USD, DISCOUNT_CURVE_USD)
        .build();
    assertThat(test.getCurves()).hasSize(2);
    assertThat(test.getCurves().get(DISCOUNT_CURVE_GBP.getName())).isEqualTo(DISCOUNT_CURVE_GBP);
    assertThat(test.getCurves().get(DISCOUNT_CURVE_USD.getName())).isEqualTo(DISCOUNT_CURVE_USD);
  }