  @Test
  public void test_presentValueSensitivity_beforeStart() {
    PointSensitivities point = PRICER.presentValueSensitivity(SWAP_PRODUCT, PROVIDER);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expectedUsd = CAL_FD.sensitivity(
        PROVIDER, (p) -> PRICER.presentValue(SWAP_PRODUCT, (p)).getAmount(USD));
    CurrencyParameterSensitivities expectedKrw = CAL_FD.sensitivity(
        PROVIDER, (p) -> PRICER.presentValue(SWAP_PRODUCT, (p)).getAmount(KRW));
    assertThat(computed.equalWithTolerance(expectedUsd.combinedWith(expectedKrw), NOMINAL_USD * FX_RATE * EPS_FD)).isTrue();
  }