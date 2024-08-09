  @Test
  public void test_priceSensitivityBlackVolatility() {
    BondFutureOptionSensitivity sensi = OPTION_PRICER.priceSensitivityModelParamsVolatility(
        FUTURE_OPTION_PRODUCT, RATE_PROVIDER, VOLS);
    testPriceSensitivityBlackVolatility(
        VOLS.parameterSensitivity(sensi),
        (p) -> OPTION_PRICER.price(FUTURE_OPTION_PRODUCT, RATE_PROVIDER, (p)));
  }