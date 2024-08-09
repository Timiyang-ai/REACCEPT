  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities point = PRICER.presentValueSensitivity(FWD, PROVIDER);
    CurrencyParameterSensitivities computed = PROVIDER.parameterSensitivity(point);
    CurrencyParameterSensitivities expectedUsd =
        CAL_FD.sensitivity(PROVIDER, (p) -> PRICER.presentValue(FWD, (p)).getAmount(USD));
    CurrencyParameterSensitivities expectedKrw =
        CAL_FD.sensitivity(PROVIDER, (p) -> PRICER.presentValue(FWD, (p)).getAmount(KRW));
    assertThat(computed.equalWithTolerance(expectedUsd.combinedWith(expectedKrw), NOMINAL_USD * FX_RATE * EPS_FD)).isTrue();
  }