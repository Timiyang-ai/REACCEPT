  @Test
  public void test_presentValue() {
    MultiCurrencyAmount pvSensiTrade = PRICER_TRADE.presentValue(OPTION_TRADE, RATES_PROVIDER, VOLS);
    CurrencyAmount pvSensiProduct = PRICER_PRODUCT.presentValue(OPTION_PRODUCT, RATES_PROVIDER, VOLS);
    CurrencyAmount pvSensiPremium = PRICER_PAYMENT.presentValue(PREMIUM, RATES_PROVIDER);
    assertThat(pvSensiTrade).isEqualTo(MultiCurrencyAmount.of(pvSensiProduct, pvSensiPremium));
  }