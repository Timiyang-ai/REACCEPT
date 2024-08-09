  @Test
  public void test_presentValueSensitivityRatesStickyModel() {
    PointSensitivityBuilder computed1 = PRICER.presentValueSensitivityRatesStickyModel(CAP_ONE_LEG, RATES, VOLS);
    PointSensitivityBuilder computed2 = PRICER.presentValueSensitivityRatesStickyModel(CAP_TWO_LEGS, RATES, VOLS);
    PointSensitivityBuilder cap = PRICER_CAP_LEG.presentValueSensitivityRatesStickyModel(CAP_LEG, RATES, VOLS);
    PointSensitivityBuilder pay = PRICER_PAY_LEG.presentValueSensitivity(PAY_LEG, RATES);
    assertThat(computed1).isEqualTo(cap);
    assertThat(computed2).isEqualTo(cap.combinedWith(pay));
  }