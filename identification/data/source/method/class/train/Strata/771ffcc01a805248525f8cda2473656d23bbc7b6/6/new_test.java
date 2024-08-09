  @Test
  public void test_presentValueSensitivity_noFixing() {
    PointSensitivities computed = PRICER.presentValueSensitivity(RDEPOSIT, IMM_PROV_NOFIX);
    CurrencyParameterSensitivities sensiComputed = IMM_PROV_NOFIX.parameterSensitivity(computed);
    CurrencyParameterSensitivities sensiExpected =
        CAL_FD.sensitivity(IMM_PROV_NOFIX, (p) -> PRICER.presentValue(RDEPOSIT, (p)));
    assertThat(sensiComputed.equalWithTolerance(sensiExpected, NOTIONAL * EPS_FD)).isTrue();
  }