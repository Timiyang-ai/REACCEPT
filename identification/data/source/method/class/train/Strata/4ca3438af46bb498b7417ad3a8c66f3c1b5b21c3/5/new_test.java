  @Test
  public void test_parSpreadSensitivity_ISDA() {
    PointSensitivities sensiSpread = DEFAULT_PRICER.parSpreadSensitivity(RFRA, IMM_PROV);
    CurrencyParameterSensitivities sensiComputed = IMM_PROV.parameterSensitivity(sensiSpread);
    CurrencyParameterSensitivities sensiExpected = CAL_FD.sensitivity(IMM_PROV,
        (p) -> CurrencyAmount.of(FRA.getCurrency(), DEFAULT_PRICER.parSpread(RFRA, (p))));
    assertThat(sensiComputed.equalWithTolerance(sensiExpected, EPS_FD)).isTrue();
    PointSensitivities sensiRate = DEFAULT_PRICER.parRateSensitivity(RFRA, IMM_PROV);
    assertThat(sensiSpread.equalWithTolerance(sensiRate, EPS_FD)).isTrue();

    // test via FraTrade
    assertThat(DEFAULT_TRADE_PRICER.parRateSensitivity(RFRA_TRADE, IMM_PROV)).isEqualTo(DEFAULT_PRICER.parRateSensitivity(RFRA, IMM_PROV));
    assertThat(DEFAULT_TRADE_PRICER.parSpreadSensitivity(RFRA_TRADE, IMM_PROV)).isEqualTo(DEFAULT_PRICER.parSpreadSensitivity(RFRA, IMM_PROV));
  }