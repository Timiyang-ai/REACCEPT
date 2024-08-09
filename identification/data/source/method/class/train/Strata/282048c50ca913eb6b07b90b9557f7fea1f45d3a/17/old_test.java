  @Test
  public void test_presentValueSensitivityStrike() {
    double computed = LEG_PRICER.presentValueSensitivityStrike(CAP_LEG, RATES_PROVIDER, VOLATILITIES);
    double expected = 0d;
    List<CmsPeriod> cms = CAP_LEG.getCmsPeriods();
    int size = cms.size();
    for (int i = 0; i < size; ++i) {
      expected += PERIOD_PRICER.presentValueSensitivityStrike(cms.get(i), RATES_PROVIDER, VOLATILITIES);
    }
    assertThat(computed).isCloseTo(expected, offset(NOTIONAL_VALUE_0 * TOL));
  }