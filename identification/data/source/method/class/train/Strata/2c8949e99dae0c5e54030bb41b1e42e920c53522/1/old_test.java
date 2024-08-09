  @Test
  public void test_presentValueSensitivityRatesStickyModel() {
    PointSensitivities computedWithPayLeg = PRICER.presentValueSensitivityRatesStickyModel(TRADE_PAYLEG, RATES, VOLS);
    PointSensitivities computedWithPremium = PRICER.presentValueSensitivityRatesStickyModel(TRADE_PREMIUM, RATES, VOLS);
    PointSensitivities pvOneLeg = PRICER_PRODUCT.presentValueSensitivityRatesStickyModel(CAP_ONE_LEG, RATES, VOLS).build();
    PointSensitivities pvTwoLegs = PRICER_PRODUCT.presentValueSensitivityRatesStickyModel(CAP_TWO_LEGS, RATES, VOLS).build();
    PointSensitivities pvPrem = PRICER_PREMIUM.presentValueSensitivity(PREMIUM, RATES).build();
    assertThat(computedWithPayLeg).isEqualTo(pvTwoLegs);
    assertThat(computedWithPremium).isEqualTo(pvOneLeg.combinedWith(pvPrem));
  }