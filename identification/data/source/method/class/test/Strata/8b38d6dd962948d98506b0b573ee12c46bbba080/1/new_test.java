  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities point = PRICER.presentValueSensitivityRates(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER);
    CurrencyParameterSensitivities computed = RATE_PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected =
        FD_CAL.sensitivity(RATE_PROVIDER, p -> PRICER.presentValue(FUTURE_TRADE, p, HW_PROVIDER, LAST_PRICE));
    assertThat(computed.equalWithTolerance(expected, NOTIONAL * QUANTITY * TOL_FD)).isTrue();
  }