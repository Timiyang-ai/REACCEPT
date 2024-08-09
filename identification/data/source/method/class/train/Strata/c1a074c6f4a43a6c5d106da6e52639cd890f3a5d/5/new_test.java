  @Test
  public void test_parSpreadSensitivity_fixedIbor() {
    ResolvedSwapTrade trade = SWAP_USD_FIXED_6M_LIBOR_3M_5Y.resolve(REF_DATA);
    PointSensitivities point = SWAP_PRODUCT_PRICER.parSpreadSensitivity(trade.getProduct(), MULTI_USD).build();
    CurrencyParameterSensitivities prAd = MULTI_USD.parameterSensitivity(point);
    CurrencyParameterSensitivities prFd = FINITE_DIFFERENCE_CALCULATOR.sensitivity(
        MULTI_USD, p -> CurrencyAmount.of(USD, SWAP_PRODUCT_PRICER.parSpread(trade.getProduct(), p)));
    assertThat(prAd.equalWithTolerance(prFd, TOLERANCE_RATE_DELTA)).isTrue();

    // test via SwapTrade
    DiscountingSwapTradePricer pricerTrade = DiscountingSwapTradePricer.DEFAULT;
    DiscountingSwapProductPricer pricerSwap = DiscountingSwapProductPricer.DEFAULT;
    assertThat(pricerTrade.parSpreadSensitivity(trade, MULTI_USD)).isEqualTo(pricerSwap.parSpreadSensitivity(trade.getProduct(), MULTI_USD).build());
  }