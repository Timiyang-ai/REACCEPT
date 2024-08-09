  @Test
  public void test_presentValueGamma() {
    CurrencyAmount pvGammaRecComputed =
        PRICER_SWAPTION.presentValueGamma(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    CurrencyAmount pvGammaPayComputed =
        PRICER_SWAPTION.presentValueGamma(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    double forward = PRICER_SWAP.parRate(RSWAP_REC, RATE_PROVIDER);
    double annuityCash = PRICER_SWAP.getLegPricer().annuityCash(RSWAP_REC.getLegs(SwapLegType.FIXED).get(0), forward);
    double volatility = VOLS.volatility(SWAPTION_REC_LONG.getExpiry(), SWAP_TENOR_YEAR, STRIKE, forward);
    double discount = RATE_PROVIDER.discountFactor(USD, SETTLE_DATE);
    NormalFunctionData normalData = NormalFunctionData.of(forward, annuityCash * discount, volatility);
    double expiry = VOLS.relativeTime(SWAPTION_REC_LONG.getExpiry());
    EuropeanVanillaOption optionRec = EuropeanVanillaOption.of(STRIKE, expiry, PutCall.PUT);
    EuropeanVanillaOption optionPay = EuropeanVanillaOption.of(STRIKE, expiry, PutCall.CALL);
    double pvGammaRecExpected = NORMAL.getGamma(optionRec, normalData);
    double pvGammaPayExpected = -NORMAL.getGamma(optionPay, normalData);
    assertThat(pvGammaRecComputed.getCurrency()).isEqualTo(USD);
    assertThat(pvGammaRecComputed.getAmount()).isCloseTo(pvGammaRecExpected, offset(NOTIONAL * TOL));
    assertThat(pvGammaPayComputed.getCurrency()).isEqualTo(USD);
    assertThat(pvGammaPayComputed.getAmount()).isCloseTo(pvGammaPayExpected, offset(NOTIONAL * TOL));
  }