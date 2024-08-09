  @Test
  public void test_presentValueSensitivityWithZSpread() {
    PointSensitivities point = PRICER.presentValueSensitivityWithZSpread(
        PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, CONTINUOUS, 0).build();
    CurrencyParameterSensitivities computed1 = RATES_PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities computed2 = ISSUER_RATES_PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected = fdPvSensitivityWithZSpread(
        PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, CONTINUOUS, 0);
    assertThat(expected.equalWithTolerance(computed1.combinedWith(computed2), EPS * NOTIONAL)).isTrue();
  }