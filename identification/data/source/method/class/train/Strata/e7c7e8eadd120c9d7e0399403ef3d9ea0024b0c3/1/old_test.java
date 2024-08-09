  @Test
  public void test_presentValueSensitivity_parity() {
    CurrencyParameterSensitivities pvSensiRecLong = RATE_PROVIDER.parameterSensitivity(
        SWAPTION_PRICER.presentValueSensitivityRatesStickyModel(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS).build());
    CurrencyParameterSensitivities pvSensiRecShort = RATE_PROVIDER.parameterSensitivity(
        SWAPTION_PRICER.presentValueSensitivityRatesStickyModel(SWAPTION_REC_SHORT, RATE_PROVIDER, VOLS).build());
    CurrencyParameterSensitivities pvSensiPayLong = RATE_PROVIDER.parameterSensitivity(
        SWAPTION_PRICER.presentValueSensitivityRatesStickyModel(SWAPTION_PAY_LONG, RATE_PROVIDER, VOLS).build());
    CurrencyParameterSensitivities pvSensiPayShort = RATE_PROVIDER.parameterSensitivity(
        SWAPTION_PRICER.presentValueSensitivityRatesStickyModel(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS).build());
    assertThat(pvSensiRecLong.equalWithTolerance(pvSensiRecShort.multipliedBy(-1d), NOTIONAL * TOL)).isTrue();
    assertThat(pvSensiPayLong.equalWithTolerance(pvSensiPayShort.multipliedBy(-1d), NOTIONAL * TOL)).isTrue();

    CurrencyParameterSensitivities pvSensiSwap = RATE_PROVIDER.parameterSensitivity(
        SWAP_PRICER.presentValueSensitivity(RSWAP_PAY, RATE_PROVIDER).build());
    assertThat(pvSensiSwap.equalWithTolerance(pvSensiPayLong.combinedWith(pvSensiRecLong.multipliedBy(-1d)),
        NOTIONAL * TOL)).isTrue();
    assertThat(pvSensiSwap.equalWithTolerance(pvSensiRecShort.combinedWith(pvSensiPayShort.multipliedBy(-1d)),
        NOTIONAL * TOL)).isTrue();
  }