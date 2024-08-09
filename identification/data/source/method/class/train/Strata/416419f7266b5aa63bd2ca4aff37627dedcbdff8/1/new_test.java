  @Test
  public void test_priceSensitivityWithZSpread_continuous() {
    PointSensitivities point = FUTURE_PRICER.priceSensitivityWithZSpread(
        FUTURE_PRODUCT, PROVIDER, Z_SPREAD, CONTINUOUS, 0);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(PROVIDER,
        (p) -> CurrencyAmount.of(USD, FUTURE_PRICER.priceWithZSpread(FUTURE_PRODUCT, (p), Z_SPREAD, CONTINUOUS, 0)));
    assertThat(computed.equalWithTolerance(expected, EPS * 10.0)).isTrue();
  }