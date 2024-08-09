  @Test
  public void test_presentValueSensitivitySabrParameter() {
    PointSensitivities pt1 =
        TRADE_PRICER.presentValueSensitivityModelParamsSabr(CMS_TRADE_PREMIUM, RATES_PROVIDER, VOLATILITIES);
    PointSensitivities pt2 =
        TRADE_PRICER.presentValueSensitivityModelParamsSabr(CMS_TRADE, RATES_PROVIDER, VOLATILITIES);
    PointSensitivities ptProd1 =
        PRODUCT_PRICER.presentValueSensitivityModelParamsSabr(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES).build();
    PointSensitivities ptProd2 =
        PRODUCT_PRICER.presentValueSensitivityModelParamsSabr(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES).build();
    assertThat(pt1).isEqualTo(ptProd1);
    assertThat(pt2).isEqualTo(ptProd2);
  }