  @Test
  public void test_currentCash() {
    MultiCurrencyAmount cc1 = PRODUCT_PRICER.currentCash(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount cc2 = PRODUCT_PRICER.currentCash(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES);
    assertThat(cc1).isEqualTo(MultiCurrencyAmount.of(CurrencyAmount.zero(EUR)));
    assertThat(cc2).isEqualTo(MultiCurrencyAmount.of(CurrencyAmount.zero(EUR)));
  }