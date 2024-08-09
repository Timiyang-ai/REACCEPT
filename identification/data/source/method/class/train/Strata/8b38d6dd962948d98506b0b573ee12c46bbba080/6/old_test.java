  @Test
  public void test_priceSensitivity() {
    PointSensitivities point = PRICER.priceSensitivityRates(FUTURE, RATE_PROVIDER, HW_PROVIDER);
    CurrencyParameterSensitivities computed = RATE_PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected =
        FD_CAL.sensitivity(RATE_PROVIDER, (p) -> CurrencyAmount.of(EUR, PRICER.price(FUTURE, (p), HW_PROVIDER)));
    assertThat(computed.equalWithTolerance(expected, TOL_FD)).isTrue();
  }