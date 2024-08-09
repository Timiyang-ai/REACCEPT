  @Test
  public void test_presentValueSensitivity() {
    PointSensitivityBuilder point = PRICER.presentValueSensitivity(PRODUCT, PROVIDER);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point.build());
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(PROVIDER, p -> PRICER.presentValue(PRODUCT, p));
    assertThat(computed.equalWithTolerance(expected, 30d * NOTIONAL * EPS)).isTrue();
  }