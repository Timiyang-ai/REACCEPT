  @Test
  public void test_presentValueSensitivityWithZSpread_continuous() {
    PointSensitivities pointTrade =
        TRADE_PRICER.presentValueSensitivityWithZSpread(TRADE, PROVIDER, Z_SPREAD, CONTINUOUS, 0);
    CurrencyParameterSensitivities computedTrade = PROVIDER.parameterSensitivity(pointTrade);
    CurrencyParameterSensitivities expectedTrade = FD_CAL.sensitivity(
        PROVIDER, (p) -> TRADE_PRICER.presentValueWithZSpread(TRADE, (p), Z_SPREAD, CONTINUOUS, 0));
    assertThat(computedTrade.equalWithTolerance(expectedTrade, 20d * NOTIONAL * QUANTITY * EPS)).isTrue();
  }