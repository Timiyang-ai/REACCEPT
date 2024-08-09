  @Test
  public void test_currencyExposure() {
    assertThat(PRICER_TRADE.currencyExposure(RFRA_TRADE, RATES_PROVIDER)).isEqualTo(MultiCurrencyAmount.of(PRICER_TRADE.presentValue(RFRA_TRADE, RATES_PROVIDER)));
  }