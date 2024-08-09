  @Test
  public void test_parSpreadSensitivity() {
    PointSensitivities computed = PRICER.parSpreadSensitivity(RTERM_DEPOSIT, IMM_PROV);
    CurrencyParameterSensitivities sensiComputed = IMM_PROV.parameterSensitivity(computed);
    CurrencyParameterSensitivities sensiExpected =
        CAL_FD.sensitivity(IMM_PROV, (p) -> CurrencyAmount.of(EUR, PRICER.parSpread(RTERM_DEPOSIT, (p))));
    assertThat(sensiComputed.equalWithTolerance(sensiExpected, NOTIONAL * EPS_FD)).isTrue();
  }