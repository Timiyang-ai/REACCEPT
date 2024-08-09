  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities point = PRICER.presentValueSensitivity(PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER).build();
    CurrencyParameterSensitivities computed1 = RATES_PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities computed2 = ISSUER_RATES_PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected = fdPvSensitivity(PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER);
    assertThat(expected.equalWithTolerance(computed1.combinedWith(computed2), EPS * NOTIONAL)).isTrue();
  }