  @Test
  public void test_netAmount_standard() {
    CurrencyAmount computed = PRICER.netAmount(TRADE_STANDARD, RATES_PROVIDER);
    double expected = PERIOD_PRICER.forecastValue(SETTLE_PERIOD_STANDARD, RATES_PROVIDER);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(QUANTITY * NOTIONAL * TOL));
  }