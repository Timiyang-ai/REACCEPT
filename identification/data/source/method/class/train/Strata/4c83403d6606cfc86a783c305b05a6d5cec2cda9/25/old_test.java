  @Test
  public void test_parSpreadSensitivity_beforeStart() {
    PointSensitivities pts = PRICER.parSpreadSensitivity(SWAP_PRODUCT, PROVIDER);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(pts);
    CurrencyParameterSensitivities expected = CAL_FD.sensitivity(
        PROVIDER, (p) -> CurrencyAmount.of(KRW, PRICER.parSpread(SWAP_PRODUCT, p)));
    assertThat(computed.equalWithTolerance(expected, TOLERANCE_SPREAD_DELTA)).isTrue();
  }