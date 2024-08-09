  @Test
  public void test_presentValue() {
    MultiCurrencyAmount pv1 = PRODUCT_PRICER.presentValue(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount pv2 = PRODUCT_PRICER.presentValue(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES);
    CurrencyAmount pvCms = CMS_LEG_PRICER.presentValue(CMS_LEG, RATES_PROVIDER, VOLATILITIES);
    CurrencyAmount pvPay = SWAP_LEG_PRICER.presentValue(PAY_LEG, RATES_PROVIDER);
    assertThat(pv1).isEqualTo(MultiCurrencyAmount.of(pvCms));
    assertThat(pv2).isEqualTo(MultiCurrencyAmount.of(pvCms).plus(pvPay));
  }