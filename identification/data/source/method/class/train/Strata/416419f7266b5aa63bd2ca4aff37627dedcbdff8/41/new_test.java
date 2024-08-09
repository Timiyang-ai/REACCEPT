  @Test
  public void test_currencyExposureWithZSpread() {
    MultiCurrencyAmount ceComputed = TRADE_PRICER.currencyExposureWithZSpread(
        TRADE, PROVIDER, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    CurrencyAmount pv = TRADE_PRICER.presentValueWithZSpread(TRADE, PROVIDER, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    assertThat(ceComputed).isEqualTo(MultiCurrencyAmount.of(pv));
  }