  @Test
  public void test_presentValueSensitivity() {
    PointSensitivityBuilder point = LEG_PRICER.presentValueSensitivityRates(FLOOR_LEG, RATES_PROVIDER, VOLATILITIES);
    CurrencyParameterSensitivities computed = RATES_PROVIDER.parameterSensitivity(point.build());
    CurrencyParameterSensitivities expected =
        FD_CAL.sensitivity(RATES_PROVIDER, p -> LEG_PRICER.presentValue(FLOOR_LEG, p, VOLATILITIES));
    assertThat(computed.equalWithTolerance(expected, EPS * NOTIONAL_VALUE_0 * 80d)).isTrue();
  }