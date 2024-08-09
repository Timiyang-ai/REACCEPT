  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities pointTrade = TRADE_PRICER.presentValueSensitivity(TRADE, PROVIDER);
    CurrencyParameterSensitivities computedTrade = PROVIDER.parameterSensitivity(pointTrade);
    CurrencyParameterSensitivities expectedTrade = FD_CAL.sensitivity(PROVIDER,
        (p) -> TRADE_PRICER.presentValue(TRADE, (p)));
    assertThat(computedTrade.equalWithTolerance(expectedTrade, 30d * NOTIONAL * QUANTITY * EPS)).isTrue();
  }