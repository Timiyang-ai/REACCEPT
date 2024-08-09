  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities computed = PRICER.presentValueSensitivity(RTERM_DEPOSIT, IMM_PROV);
    CurrencyParameterSensitivities sensiComputed = IMM_PROV.parameterSensitivity(computed);
    CurrencyParameterSensitivities sensiExpected =
        CAL_FD.sensitivity(IMM_PROV, (p) -> PRICER.presentValue(RTERM_DEPOSIT, (p)));
    assertThat(sensiComputed.equalWithTolerance(sensiExpected, NOTIONAL * EPS_FD)).isTrue();
  }