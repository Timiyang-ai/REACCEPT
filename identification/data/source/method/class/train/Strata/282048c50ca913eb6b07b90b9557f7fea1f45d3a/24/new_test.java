  @Test
  public void test_presentValueSensitivitySabrParameter() {
    PointSensitivities pt1 =
        PRODUCT_PRICER.presentValueSensitivityModelParamsSabr(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES).build();
    PointSensitivities pt2 =
        PRODUCT_PRICER.presentValueSensitivityModelParamsSabr(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES).build();
    PointSensitivities ptCms =
        CMS_LEG_PRICER.presentValueSensitivityModelParamsSabr(CMS_LEG, RATES_PROVIDER, VOLATILITIES).build();
    assertThat(pt1).isEqualTo(ptCms);
    assertThat(pt2).isEqualTo(ptCms);
  }