  @Test
  public void test_presentValueSensitivityStrike() {
    double sensi1 = TRADE_PRICER.presentValueSensitivityStrike(CMS_TRADE_PREMIUM, RATES_PROVIDER, VOLATILITIES);
    double sensi2 = TRADE_PRICER.presentValueSensitivityStrike(CMS_TRADE, RATES_PROVIDER, VOLATILITIES);
    double sensiProd1 = PRODUCT_PRICER.presentValueSensitivityStrike(CMS_ONE_LEG, RATES_PROVIDER, VOLATILITIES);
    double sensiProd2 = PRODUCT_PRICER.presentValueSensitivityStrike(CMS_TWO_LEGS, RATES_PROVIDER, VOLATILITIES);
    assertThat(sensi1).isEqualTo(sensiProd1);
    assertThat(sensi2).isEqualTo(sensiProd2);
  }