  @Test
  public void test_currencyExposure_singleCurrency() {
    PointSensitivities point = SWAP_PRODUCT_PRICER.parRateSensitivity(SWAP, RATES_GBP).build();
    MultiCurrencyAmount expected = RATES_GBP.currencyExposure(point)
        .plus(SWAP_PRODUCT_PRICER.presentValue(SWAP, RATES_GBP));
    MultiCurrencyAmount computed = SWAP_PRODUCT_PRICER.currencyExposure(SWAP, RATES_GBP);
    assertThat(computed).isEqualTo(expected);
    MultiCurrencyAmount fromTrade = SWAP_TRADE_PRICER.currencyExposure(SWAP_TRADE, RATES_GBP);
    assertThat(fromTrade).isEqualTo(computed);
  }