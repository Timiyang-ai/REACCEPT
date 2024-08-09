  @Test
  public void test_presentValueTheta() {
    CurrencyAmount pvThetaRecComputed =
        PRICER_SWAPTION.presentValueTheta(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    CurrencyAmount pvThetaPayComputed =
        PRICER_SWAPTION.presentValueTheta(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    double forward = PRICER_SWAP.parRate(RSWAP_REC, RATE_PROVIDER);
    double annuityCash = PRICER_SWAP.getLegPricer().annuityCash(RSWAP_REC.getLegs(SwapLegType.FIXED).get(0), forward);
    double volatility = VOLS.volatility(SWAPTION_REC_LONG.getExpiry(), SWAP_TENOR_YEAR, STRIKE,
        forward);
    double discount = RATE_PROVIDER.discountFactor(USD, SETTLE_DATE);
    NormalFunctionData normalData = NormalFunctionData.of(forward, annuityCash * discount, volatility);
    double expiry = VOLS.relativeTime(SWAPTION_REC_LONG.getExpiry());
    EuropeanVanillaOption optionRec = EuropeanVanillaOption.of(STRIKE, expiry, PutCall.PUT);
    EuropeanVanillaOption optionPay = EuropeanVanillaOption.of(STRIKE, expiry, PutCall.CALL);
    double pvThetaRecExpected = NORMAL.getTheta(optionRec, normalData);
    double pvThetaPayExpected = -NORMAL.getTheta(optionPay, normalData);
    assertThat(pvThetaRecComputed.getCurrency()).isEqualTo(USD);
    assertThat(pvThetaRecComputed.getAmount()).isCloseTo(pvThetaRecExpected, offset(NOTIONAL * TOL));
    assertThat(pvThetaPayComputed.getCurrency()).isEqualTo(USD);
    assertThat(pvThetaPayComputed.getAmount()).isCloseTo(pvThetaPayExpected, offset(NOTIONAL * TOL));
  }