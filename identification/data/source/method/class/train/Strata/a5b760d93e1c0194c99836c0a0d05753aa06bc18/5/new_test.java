  @Test
  public void test_parRateSensitivity_singleCurrency() {
    PointSensitivities point = SWAP_PRODUCT_PRICER.parRateSensitivity(SWAP, RATES_GBP).build();
    CurrencyParameterSensitivities prAd = RATES_GBP.parameterSensitivity(point);
    CurrencyParameterSensitivities prFd = FINITE_DIFFERENCE_CALCULATOR.sensitivity(
        RATES_GBP, p -> CurrencyAmount.of(GBP, SWAP_PRODUCT_PRICER.parRate(SWAP, p)));
    assertThat(prAd.equalWithTolerance(prFd, TOLERANCE_RATE_DELTA)).isTrue();

    // test via SwapTrade
    DiscountingSwapTradePricer pricerTrade = DiscountingSwapTradePricer.DEFAULT;
    DiscountingSwapProductPricer pricerSwap = DiscountingSwapProductPricer.DEFAULT;
    assertThat(pricerTrade.parRateSensitivity(SWAP_TRADE, RATES_GBP)).isEqualTo(pricerSwap.parRateSensitivity(SWAP, RATES_GBP).build());
  }