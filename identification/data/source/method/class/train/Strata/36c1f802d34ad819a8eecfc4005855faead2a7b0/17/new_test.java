  @Test
  public void test_presentValueSensitivityWithZSpread_continuous() {
    PointSensitivityBuilder point = PRICER.presentValueSensitivityWithZSpread(PRODUCT, PROVIDER, Z_SPREAD, CONTINUOUS,
        0);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point.build());
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(
        PROVIDER, p -> PRICER.presentValueWithZSpread(PRODUCT, p, Z_SPREAD, CONTINUOUS, 0));
    assertThat(computed.equalWithTolerance(expected, 20d * NOTIONAL * EPS)).isTrue();
  }