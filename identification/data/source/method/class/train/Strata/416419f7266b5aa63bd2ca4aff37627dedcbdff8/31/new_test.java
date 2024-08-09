  @Test
  public void test_priceSensitivity() {
    PointSensitivities point = FUTURE_PRICER.priceSensitivity(FUTURE_PRODUCT, PROVIDER);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(PROVIDER,
        (p) -> CurrencyAmount.of(USD, FUTURE_PRICER.price(FUTURE_PRODUCT, (p))));
    assertThat(computed.equalWithTolerance(expected, EPS * 10.0)).isTrue();
  }