  @Test
  public void test_presentValueSensitivityStrike() {
    double sensi1 = PRODUCT_PRICER.presentValueSensitivityStrike(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES);
    double sensi2 = PRODUCT_PRICER.presentValueSensitivityStrike(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES);
    double sensiCms = CMS_LEG_PRICER.presentValueSensitivityStrike(CMS_LEG, RATES_PROVIDER, VOLATILITIES);
    assertThat(sensi1).isEqualTo(sensiCms);
    assertThat(sensi2).isEqualTo(sensiCms);
  }