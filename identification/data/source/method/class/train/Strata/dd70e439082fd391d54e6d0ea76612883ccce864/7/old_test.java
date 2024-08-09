  @Test
  public void test_presentValueSensitivity() {
    // call
    PointSensitivities pointCall = PRICER.presentValueSensitivityRatesStickyStrike(CALL_OTM, RATES_PROVIDER, VOLS);
    CurrencyParameterSensitivities computedCall = RATES_PROVIDER.parameterSensitivity(pointCall);
    CurrencyParameterSensitivities expectedCall = FD_CAL.sensitivity(
        RATES_PROVIDER, (p) -> PRICER.presentValue(CALL_OTM, (p), VOLS));
    // contribution via implied volatility, to be subtracted.
    CurrencyAmount pvVegaCall = PRICER.presentValueVega(CALL_OTM, RATES_PROVIDER, VOLS);
    CurrencyParameterSensitivities impliedVolSenseCall =
        FD_CAL.sensitivity(RATES_PROVIDER,
            (p) -> CurrencyAmount.of(USD, PRICER.impliedVolatility(CALL_OTM, (p), VOLS)))
            .multipliedBy(-pvVegaCall.getAmount());
    assertThat(computedCall.equalWithTolerance(expectedCall.combinedWith(impliedVolSenseCall), NOTIONAL * FD_EPS)).isTrue();
    // put
    PointSensitivities pointPut = PRICER.presentValueSensitivityRatesStickyStrike(PUT_OTM, RATES_PROVIDER, VOLS);
    CurrencyParameterSensitivities computedPut = RATES_PROVIDER.parameterSensitivity(pointPut);
    CurrencyParameterSensitivities expectedPut = FD_CAL.sensitivity(
        RATES_PROVIDER, (p) -> PRICER.presentValue(PUT_OTM, (p), VOLS));
    // contribution via implied volatility, to be subtracted.
    CurrencyAmount pvVegaPut = PRICER.presentValueVega(PUT_OTM, RATES_PROVIDER, VOLS);
    CurrencyParameterSensitivities impliedVolSensePut = FD_CAL.sensitivity(
        RATES_PROVIDER, (p) -> CurrencyAmount.of(USD, PRICER.impliedVolatility(PUT_OTM, (p), VOLS)))
        .multipliedBy(-pvVegaPut.getAmount());
    assertThat(computedPut.equalWithTolerance(expectedPut.combinedWith(impliedVolSensePut), NOTIONAL * FD_EPS)).isTrue();
  }