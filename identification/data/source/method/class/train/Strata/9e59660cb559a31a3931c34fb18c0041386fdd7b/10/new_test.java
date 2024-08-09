  @Test
  public void test_presentValueSensitivity() {
    PointSensitivityBuilder pointRec =
        PRICER.presentValueSensitivityRates(SWAPTION_REC_LONG, RATE_PROVIDER, HW_PROVIDER);
    CurrencyParameterSensitivities computedRec = RATE_PROVIDER.parameterSensitivity(pointRec.build());
    CurrencyParameterSensitivities expectedRec =
        FD_CAL.sensitivity(RATE_PROVIDER, (p) -> PRICER.presentValue(SWAPTION_REC_LONG, (p), HW_PROVIDER));
    assertThat(computedRec.equalWithTolerance(expectedRec, NOTIONAL * FD_TOL * 1000d)).isTrue();
    PointSensitivityBuilder pointPay =
        PRICER.presentValueSensitivityRates(SWAPTION_PAY_SHORT, RATE_PROVIDER, HW_PROVIDER);
    CurrencyParameterSensitivities computedPay = RATE_PROVIDER.parameterSensitivity(pointPay.build());
    CurrencyParameterSensitivities expectedPay =
        FD_CAL.sensitivity(RATE_PROVIDER, (p) -> PRICER.presentValue(SWAPTION_PAY_SHORT, (p), HW_PROVIDER));
    assertThat(computedPay.equalWithTolerance(expectedPay, NOTIONAL * FD_TOL * 1000d)).isTrue();
  }