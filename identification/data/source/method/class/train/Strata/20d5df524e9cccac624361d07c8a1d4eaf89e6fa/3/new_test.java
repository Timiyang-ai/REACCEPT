  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount ceComputed = OPTION_TRADE_PRICER.currencyExposure(
        OPTION_TRADE, RATE_PROVIDER, VOLS, REFERENCE_PRICE);
    CurrencyAmount pv = OPTION_TRADE_PRICER.presentValue(OPTION_TRADE, RATE_PROVIDER, VOLS, REFERENCE_PRICE);
    assertThat(ceComputed).isEqualTo(MultiCurrencyAmount.of(pv));
  }