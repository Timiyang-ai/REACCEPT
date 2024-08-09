  @Test
  public void test_fxForwardRates() {
    ImmutableRatesProvider test = ImmutableRatesProvider.builder(VAL_DATE)
        .fxRateProvider(FX_MATRIX)
        .discountCurve(GBP, DISCOUNT_CURVE_GBP)
        .discountCurve(USD, DISCOUNT_CURVE_USD)
        .build();
    DiscountFxForwardRates res = (DiscountFxForwardRates) test.fxForwardRates(CurrencyPair.of(GBP, USD));
    assertThat(res.getBaseCurrencyDiscountFactors()).isEqualTo(ZeroRateDiscountFactors.of(GBP, VAL_DATE, DISCOUNT_CURVE_GBP));
    assertThat(res.getCounterCurrencyDiscountFactors()).isEqualTo(ZeroRateDiscountFactors.of(USD, VAL_DATE, DISCOUNT_CURVE_USD));
    assertThat(res.getCurrencyPair()).isEqualTo(CurrencyPair.of(GBP, USD));
    assertThat(res.getFxRateProvider()).isEqualTo(FX_MATRIX);
    assertThat(res.getValuationDate()).isEqualTo(VAL_DATE);
  }