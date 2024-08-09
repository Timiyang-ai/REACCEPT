  @Test
  public void test_dirtyPriceSensitivity() {
    PointSensitivityBuilder point = PRICER.dirtyPriceSensitivity(PRODUCT, PROVIDER, REF_DATA);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point.build());
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(
        PROVIDER, p -> CurrencyAmount.of(EUR, PRICER.dirtyPriceFromCurves(PRODUCT, p, REF_DATA)));
    assertThat(computed.equalWithTolerance(expected, NOTIONAL * EPS)).isTrue();
  }