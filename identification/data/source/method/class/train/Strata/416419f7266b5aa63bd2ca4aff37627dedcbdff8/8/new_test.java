  @Test
  public void test_presentValueSensitivityBlackVolatility() {
    BondFutureOptionSensitivity sensi = OPTION_TRADE_PRICER.presentValueSensitivityModelParamsVolatility(
        OPTION_TRADE, RATE_PROVIDER, VOLS);
    testPriceSensitivityBlackVolatility(VOLS.parameterSensitivity(sensi),
        (p) -> OPTION_TRADE_PRICER.presentValue(OPTION_TRADE, RATE_PROVIDER, (p), REFERENCE_PRICE).getAmount());
  }