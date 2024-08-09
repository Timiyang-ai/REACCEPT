  @Test
  public void test_parSpread_fixedIbor() {
    ResolvedSwapTrade swapTrade = SWAP_USD_FIXED_6M_LIBOR_3M_5Y.resolve(REF_DATA);
    double ps = SWAP_PRODUCT_PRICER.parSpread(swapTrade.getProduct(), MULTI_USD);
    SwapTrade swap0 = FixedIborSwapTemplate
        .of(Period.ZERO, TENOR_5Y, USD_FIXED_6M_LIBOR_3M)
        .createTrade(MULTI_USD.getValuationDate(), BUY, NOTIONAL_SWAP, FIXED_RATE + ps, REF_DATA);
    CurrencyAmount pv0 = SWAP_PRODUCT_PRICER.presentValue(swap0.getProduct().resolve(REF_DATA), USD, MULTI_USD);
    assertThat(pv0.getAmount()).isCloseTo(0, offset(TOLERANCE_PV));

    // test via SwapTrade
    DiscountingSwapProductPricer pricerSwap = DiscountingSwapProductPricer.DEFAULT;
    DiscountingSwapTradePricer pricerTrade = DiscountingSwapTradePricer.DEFAULT;
    assertThat(pricerTrade.parSpread(swapTrade, MULTI_USD)).isEqualTo(pricerSwap.parSpread(swapTrade.getProduct(), MULTI_USD));
  }