  @Test
  public void test_parSpreadSensitivity_noFixing() {
    PointSensitivities computedNoFix = PRICER.parSpreadSensitivity(RDEPOSIT, IMM_PROV_NOFIX);
    CurrencyParameterSensitivities sensiComputedNoFix = IMM_PROV_NOFIX.parameterSensitivity(computedNoFix);
    CurrencyParameterSensitivities sensiExpected =
        CAL_FD.sensitivity(IMM_PROV_NOFIX, (p) -> CurrencyAmount.of(EUR, PRICER.parSpread(RDEPOSIT, (p))));
    assertThat(sensiComputedNoFix.equalWithTolerance(sensiExpected, TOLERANCE_RATE_DELTA)).isTrue();
    // Par rate and par spread sensitivities are equal
    PointSensitivities computedParRateNoFix = PRICER.parRateSensitivity(RDEPOSIT, IMM_PROV_NOFIX);
    CurrencyParameterSensitivities sensiComputedParRateNoFix = IMM_PROV_NOFIX.parameterSensitivity(computedParRateNoFix);
    assertThat(sensiComputedNoFix.equalWithTolerance(sensiComputedParRateNoFix, TOLERANCE_RATE_DELTA)).isTrue();
    PointSensitivities computedFix = PRICER.parSpreadSensitivity(RDEPOSIT, IMM_PROV_FIX);
    CurrencyParameterSensitivities sensiComputedFix = IMM_PROV_NOFIX.parameterSensitivity(computedFix);
    assertThat(sensiComputedFix.equalWithTolerance(sensiExpected, TOLERANCE_RATE_DELTA)).isTrue();
  }