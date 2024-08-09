  @Test
  public void test_presentValueSensitivityBlackVolatility() {
    PointSensitivities pvSensiTrade =
        PRICER_TRADE.presentValueSensitivityModelParamsVolatility(OPTION_TRADE, RATES_PROVIDER, VOLS);
    PointSensitivities pvSensiProduct =
        PRICER_PRODUCT.presentValueSensitivityModelParamsVolatility(OPTION_PRODUCT, RATES_PROVIDER, VOLS).build();
    assertThat(pvSensiTrade).isEqualTo(pvSensiProduct);
  }