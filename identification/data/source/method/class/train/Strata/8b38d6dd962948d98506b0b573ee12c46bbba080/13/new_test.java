  @Test
  public void test_parSpreadSensitivity() {
    PointSensitivities point = PRICER.parSpreadSensitivityRates(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER);
    CurrencyParameterSensitivities computed = RATE_PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(RATE_PROVIDER,
        p -> CurrencyAmount.of(EUR, PRICER.parSpread(FUTURE_TRADE, p, HW_PROVIDER, LAST_PRICE)));
    assertThat(computed.equalWithTolerance(expected, NOTIONAL * QUANTITY * TOL_FD)).isTrue();
  }