  @Test
  public void test_presentValueSensitivity() {
    PointSensitivityBuilder pt1 = PRODUCT_PRICER.presentValueSensitivityRates(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES);
    PointSensitivityBuilder pt2 = PRODUCT_PRICER.presentValueSensitivityRates(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES);
    PointSensitivityBuilder ptCms =
        CMS_LEG_PRICER.presentValueSensitivityRates(CMS_LEG, RATES_PROVIDER, VOLATILITIES);
    PointSensitivityBuilder ptPay = SWAP_LEG_PRICER.presentValueSensitivity(PAY_LEG, RATES_PROVIDER);
    assertThat(pt1).isEqualTo(ptCms);
    assertThat(pt2).isEqualTo(ptCms.combinedWith(ptPay));
  }