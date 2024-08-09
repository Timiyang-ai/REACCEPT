  @Test
  public void test_discountFactors() {
    ImmutableRatesProvider test = ImmutableRatesProvider.builder(VAL_DATE)
        .discountCurve(GBP, DISCOUNT_CURVE_GBP)
        .discountCurve(USD, DISCOUNT_CURVE_USD)
        .build();
    assertThat(test.discountFactors(GBP).getCurrency()).isEqualTo(GBP);
  }