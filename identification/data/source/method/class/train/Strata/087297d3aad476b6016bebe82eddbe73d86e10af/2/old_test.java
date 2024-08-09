  @Test
  public void test_priceSensitivity() {
    PointSensitivities point = PRICER.priceSensitivity(FUTURE, PROVIDER);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected =
        FD_CAL.sensitivity(PROVIDER, (p) -> CurrencyAmount.of(USD, PRICER.price(FUTURE, (p))));
    assertThat(computed.equalWithTolerance(expected, 10d * EPS)).isTrue();
  }