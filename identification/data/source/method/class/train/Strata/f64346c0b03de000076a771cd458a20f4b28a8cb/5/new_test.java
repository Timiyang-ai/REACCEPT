  @Test
  public void presentValueSensitivity() {
    PointSensitivities sensiComputed = PRICER.presentValueSensitivity(BILL, PROVIDER);
    PointSensitivities sensiExpected = IssuerCurveDiscountFactors.of(DSC_FACTORS_ISSUER, GROUP_ISSUER)
        .zeroRatePointSensitivity(MATURITY_DATE)
        .multipliedBy(NOTIONAL.getAmount()).build();
    assertThat(sensiComputed.equalWithTolerance(sensiExpected, TOLERANCE_PV)).isTrue();
    CurrencyParameterSensitivities paramSensiComputed = PROVIDER.parameterSensitivity(sensiComputed);
    CurrencyParameterSensitivities paramSensiExpected = FD_CALC.sensitivity(PROVIDER, p -> PRICER.presentValue(BILL, p));
    assertThat(paramSensiComputed.equalWithTolerance(paramSensiExpected, EPS * NOTIONAL_AMOUNT)).isTrue();
  }