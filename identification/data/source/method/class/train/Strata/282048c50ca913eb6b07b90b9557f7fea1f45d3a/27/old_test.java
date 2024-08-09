  @Test
  public void test_currentCash() {
    MultiCurrencyAmount cc1 = TRADE_PRICER.currentCash(CMS_TRADE_PREMIUM, RATES_PROVIDER, VOLATILITIES);
    MultiCurrencyAmount cc2 = TRADE_PRICER.currentCash(CMS_TRADE, RATES_PROVIDER, VOLATILITIES);
    assertThat(cc1).isEqualTo(MultiCurrencyAmount.of(PREMIUM.getValue()));
    assertThat(cc2).isEqualTo(MultiCurrencyAmount.of(CurrencyAmount.zero(EUR)));
  }