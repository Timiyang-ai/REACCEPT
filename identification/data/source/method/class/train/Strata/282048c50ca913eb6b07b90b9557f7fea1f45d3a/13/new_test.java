  @Test
  public void test_presentValue() {
    MultiCurrencyAmount pv1 = TRADE_PRICER.presentValue(CMS_TRADE_PREMIUM, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount pv2 = TRADE_PRICER.presentValue(CMS_TRADE, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount pvProd1 = PRODUCT_PRICER.presentValue(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount pvProd2 = PRODUCT_PRICER.presentValue(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES);
    CurrencyAmount pvPrem = PREMIUM_PRICER.presentValue(PREMIUM, RATES_PROVIDER);
    assertThat(pv1).isEqualTo(pvProd1.plus(pvPrem));
    assertThat(pv2).isEqualTo(pvProd2);
  }