  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities point = PRICER.presentValueSensitivity(NDF, PROVIDER);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected = CAL_FD.sensitivity(PROVIDER, (p) -> PRICER.presentValue(NDF, (p)));
    assertThat(computed.equalWithTolerance(expected, NOMINAL_USD * EPS_FD)).isTrue();
  }