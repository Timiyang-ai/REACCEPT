  @Test
  public void test_presentValue() {
    CurrencyAmount pvRecComputed = PRICER_SWAPTION.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    CurrencyAmount pvPayComputed = PRICER_SWAPTION.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    double forward = PRICER_SWAP.parRate(RSWAP_REC, RATE_PROVIDER);
    double annuityCash = PRICER_SWAP.getLegPricer().annuityCash(RSWAP_REC.getLegs(SwapLegType.FIXED).get(0), forward);
    double volatility = VOLS.volatility(SWAPTION_REC_LONG.getExpiry(), SWAP_TENOR_YEAR, STRIKE, forward);
    double discount = RATE_PROVIDER.discountFactor(USD, SETTLE_DATE);
    NormalFunctionData normalData = NormalFunctionData.of(forward, annuityCash * discount, volatility);
    double expiry = VOLS.relativeTime(SWAPTION_REC_LONG.getExpiry());
    EuropeanVanillaOption optionRec = EuropeanVanillaOption.of(STRIKE, expiry, PutCall.PUT);
    EuropeanVanillaOption optionPay = EuropeanVanillaOption.of(STRIKE, expiry, PutCall.CALL);
    double pvRecExpected = NORMAL.getPriceFunction(optionRec).apply(normalData);
    double pvPayExpected = -NORMAL.getPriceFunction(optionPay).apply(normalData);
    assertThat(pvRecComputed.getCurrency()).isEqualTo(USD);
    assertThat(pvRecComputed.getAmount()).isCloseTo(pvRecExpected, offset(NOTIONAL * TOL));
    assertThat(pvPayComputed.getCurrency()).isEqualTo(USD);
    assertThat(pvPayComputed.getAmount()).isCloseTo(pvPayExpected, offset(NOTIONAL * TOL));
  }