  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities pt1 = TRADE_PRICER.presentValueSensitivity(CMS_TRADE_PREMIUM, RATES_PROVIDER);
    PointSensitivities pt2 = TRADE_PRICER.presentValueSensitivity(CMS_TRADE, RATES_PROVIDER);
    PointSensitivityBuilder ptProd1 = PRODUCT_PRICER.presentValueSensitivity(CMS_ONE_LEG, RATES_PROVIDER);
    PointSensitivityBuilder ptProd2 = PRODUCT_PRICER.presentValueSensitivity(CMS_TWO_LEGS, RATES_PROVIDER);
    PointSensitivityBuilder ptPrem = PREMIUM_PRICER.presentValueSensitivity(PREMIUM, RATES_PROVIDER);
    assertThat(pt1).isEqualTo(ptProd1.combinedWith(ptPrem).build());
    assertThat(pt2).isEqualTo(ptProd2.build());
  }