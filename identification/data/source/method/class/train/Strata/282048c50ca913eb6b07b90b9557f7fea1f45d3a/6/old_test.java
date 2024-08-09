  @Test
  public void test_presentValue() {
    CurrencyAmount computed = LEG_PRICER.presentValue(CAP_LEG, RATES_PROVIDER, VOLATILITIES);
    double expected = 0d;
    List<CmsPeriod> cms = CAP_LEG.getCmsPeriods();
    int size = cms.size();
    for (int i = 0; i < size; ++i) {
      expected += PERIOD_PRICER.presentValue(cms.get(i), RATES_PROVIDER, VOLATILITIES).getAmount();
    }
    assertThat(computed.getCurrency()).isEqualTo(EUR);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(NOTIONAL_VALUE_0 * TOL));
  }