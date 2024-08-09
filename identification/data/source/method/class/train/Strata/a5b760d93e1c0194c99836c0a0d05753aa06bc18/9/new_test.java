  @Test
  public void test_currencyExposure() {
    ResolvedSwapLeg expSwapLeg = IBOR_SWAP_LEG_REC_GBP;
    PointSensitivities point = PRICER_LEG.presentValueSensitivity(expSwapLeg, RATES_GBP).build();
    MultiCurrencyAmount expected = RATES_GBP.currencyExposure(point).plus(PRICER_LEG.presentValue(expSwapLeg, RATES_GBP));
    MultiCurrencyAmount computed = PRICER_LEG.currencyExposure(expSwapLeg, RATES_GBP);
    assertThat(computed).isEqualTo(expected);
  }