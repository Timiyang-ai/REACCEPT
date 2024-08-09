  @Test
  public void test_dirtyPriceSensitivityWithZspread_continuous() {
    PointSensitivityBuilder point =
        PRICER.dirtyPriceSensitivityWithZspread(PRODUCT, PROVIDER, REF_DATA, Z_SPREAD, CONTINUOUS, 0);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point.build());
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(PROVIDER, p -> CurrencyAmount.of(
        EUR, PRICER.dirtyPriceFromCurvesWithZSpread(PRODUCT, p, REF_DATA, Z_SPREAD, CONTINUOUS, 0)));
    assertThat(computed.equalWithTolerance(expected, NOTIONAL * EPS)).isTrue();
  }