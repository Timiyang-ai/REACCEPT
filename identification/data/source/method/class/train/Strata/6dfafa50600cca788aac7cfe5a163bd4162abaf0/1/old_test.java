  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities point = TRADE_PRICER.presentValueSensitivity(FUTURE_TRADE, PROVIDER);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(
        PROVIDER, (p) -> TRADE_PRICER.presentValue(FUTURE_TRADE, (p), LASTMARG_PRICE));
    assertThat(computed.equalWithTolerance(expected, NOTIONAL * QUANTITY * EPS * 10d)).isTrue();
  }