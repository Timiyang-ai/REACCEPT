  @Test
  public void test_currencyExposure() {
    CurrencyAmount pv = TRADE_PRICER.presentValue(FUTURE_TRADE, PROVIDER, LASTMARG_PRICE);
    PointSensitivities point = TRADE_PRICER.presentValueSensitivity(FUTURE_TRADE, PROVIDER);
    MultiCurrencyAmount expected = PROVIDER.currencyExposure(point).plus(pv);
    MultiCurrencyAmount computed = TRADE_PRICER.currencyExposure(FUTURE_TRADE, PROVIDER, LASTMARG_PRICE);
    assertThat(computed).isEqualTo(expected);
  }