  @Test
  public void test_presentValueSensitivitySabrParameter() {
    PointSensitivityBuilder computed =
        LEG_PRICER.presentValueSensitivityModelParamsSabr(FLOOR_LEG, RATES_PROVIDER, VOLATILITIES);
    PointSensitivityBuilder expected = PointSensitivityBuilder.none();
    List<CmsPeriod> cms = FLOOR_LEG.getCmsPeriods();
    int size = cms.size();
    for (int i = 0; i < size; ++i) {
      expected = expected.combinedWith(
          PERIOD_PRICER.presentValueSensitivityModelParamsSabr(cms.get(i), RATES_PROVIDER, VOLATILITIES));
    }
    assertThat(computed).isEqualTo(expected);
  }