  @Test
  public void test_presentValueSensitivityRatesStickyModel() {
    PointSensitivityBuilder pointRec =
        PRICER.presentValueSensitivityRatesStickyModel(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    CurrencyParameterSensitivities computedRec = RATE_PROVIDER.parameterSensitivity(pointRec.build());
    CurrencyParameterSensitivities expectedRec =
        FD_CAL.sensitivity(RATE_PROVIDER, (p) -> PRICER.presentValue(SWAPTION_REC_LONG, (p), VOLS));
    assertThat(computedRec.equalWithTolerance(expectedRec, NOTIONAL * FD_EPS * 200d)).isTrue();
    PointSensitivityBuilder pointPay =
        PRICER.presentValueSensitivityRatesStickyModel(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    CurrencyParameterSensitivities computedPay = RATE_PROVIDER.parameterSensitivity(pointPay.build());
    CurrencyParameterSensitivities expectedPay =
        FD_CAL.sensitivity(RATE_PROVIDER, (p) -> PRICER.presentValue(SWAPTION_PAY_SHORT, (p), VOLS));
    assertThat(computedPay.equalWithTolerance(expectedPay, NOTIONAL * FD_EPS * 200d)).isTrue();
  }