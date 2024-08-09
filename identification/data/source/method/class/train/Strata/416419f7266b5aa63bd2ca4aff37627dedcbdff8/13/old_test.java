  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount ceComputed = TRADE_PRICER.currencyExposure(TRADE, PROVIDER);
    CurrencyAmount pv = TRADE_PRICER.presentValue(TRADE, PROVIDER);
    assertThat(ceComputed).isEqualTo(MultiCurrencyAmount.of(pv));
  }