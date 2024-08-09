  @Test
  public void test_presentValueDelta() {
    CurrencyAmount pvDeltaRecComputed =
        PRICER_SWAPTION.presentValueDelta(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    CurrencyAmount pvDeltaPayComputed =
        PRICER_SWAPTION.presentValueDelta(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    double forward = PRICER_SWAP.parRate(RSWAP_REC, RATE_PROVIDER);
    double annuityCash = PRICER_SWAP.getLegPricer().annuityCash(RSWAP_REC.getLegs(SwapLegType.FIXED).get(0), forward);
    double volatility = VOLS.volatility(SWAPTION_REC_LONG.getExpiry(), SWAP_TENOR_YEAR, STRIKE, forward);
    double discount = RATE_PROVIDER.discountFactor(USD, SETTLE_DATE);
    NormalFunctionData normalData = NormalFunctionData.of(forward, annuityCash * discount, volatility);
    double expiry = VOLS.relativeTime(SWAPTION_REC_LONG.getExpiry());
    EuropeanVanillaOption optionRec = EuropeanVanillaOption.of(STRIKE, expiry, PutCall.PUT);
    EuropeanVanillaOption optionPay = EuropeanVanillaOption.of(STRIKE, expiry, PutCall.CALL);
    double pvDeltaRecExpected = NORMAL.getDelta(optionRec, normalData);
    double pvDeltaPayExpected = -NORMAL.getDelta(optionPay, normalData);
    assertThat(pvDeltaRecComputed.getCurrency()).isEqualTo(USD);
    assertThat(pvDeltaRecComputed.getAmount()).isCloseTo(pvDeltaRecExpected, offset(NOTIONAL * TOL));
    assertThat(pvDeltaPayComputed.getCurrency()).isEqualTo(USD);
    assertThat(pvDeltaPayComputed.getAmount()).isCloseTo(pvDeltaPayExpected, offset(NOTIONAL * TOL));
  }