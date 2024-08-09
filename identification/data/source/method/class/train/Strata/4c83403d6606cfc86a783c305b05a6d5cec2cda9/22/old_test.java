  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities pvSensiTrade = PRICER_TRADE.presentValueSensitivityRatesStickyStrike(
        OPTION_TRADE, RATES_PROVIDER, VOLS);
    PointSensitivities pvSensiProduct = PRICER_PRODUCT.presentValueSensitivityRatesStickyStrike(
        OPTION_PRODUCT, RATES_PROVIDER, VOLS);
    PointSensitivities pvSensiPremium = PRICER_PAYMENT.presentValueSensitivity(PREMIUM, RATES_PROVIDER).build();
    assertThat(pvSensiTrade).isEqualTo(pvSensiProduct.combinedWith(pvSensiPremium));
  }