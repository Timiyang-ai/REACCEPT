  @Test
  public void test_delta_presentValueDelta() {
    double computedDeltaCall = PRICER.delta(CALL_UKI, RATE_PROVIDER, VOLS);
    double computedDeltaPut = PRICER.delta(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPvDeltaCall = PRICER.presentValueDelta(CALL_UKI, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPvDeltaPut = PRICER.presentValueDelta(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    double rateBase = RATE_PROVIDER.discountFactors(EUR).zeroRate(PAY_DATE);
    double rateCounter = RATE_PROVIDER.discountFactors(USD).zeroRate(PAY_DATE);
    double costOfCarry = rateCounter - rateBase;
    double forward = RATE_PROVIDER.fxForwardRates(CURRENCY_PAIR).rate(EUR, PAY_DATE);
    double volatility = VOLS.volatility(CURRENCY_PAIR, EXPIRY_DATETIME, STRIKE_RATE, forward);
    double timeToExpiry = VOLS.relativeTime(EXPIRY_DATETIME);
    double rebateRate = REBATE_AMOUNT / NOTIONAL;
    double expectedCash = CASH_REBATE_PRICER
        .priceAdjoint(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKO).getDerivative(0);
    double expectedAsset = ASSET_REBATE_PRICER
        .priceAdjoint(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKI).getDerivative(0);
    double expectedDeltaCall = BARRIER_PRICER.priceAdjoint(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, true, BARRIER_UKI).getDerivative(0) + rebateRate * expectedCash;
    double expectedDeltaPut = BARRIER_PRICER.priceAdjoint(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, false, BARRIER_UKO).getDerivative(0) + rebateRate * expectedAsset;
    assertThat(computedDeltaCall).isCloseTo(expectedDeltaCall, offset(TOL));
    assertThat(computedDeltaPut).isCloseTo(expectedDeltaPut, offset(TOL));
    assertThat(computedPvDeltaCall.getCurrency()).isEqualTo(USD);
    assertThat(computedPvDeltaPut.getCurrency()).isEqualTo(USD);
    assertThat(computedPvDeltaCall.getAmount()).isCloseTo(expectedDeltaCall * NOTIONAL, offset(TOL));
    assertThat(computedPvDeltaPut.getAmount()).isCloseTo(-expectedDeltaPut * NOTIONAL, offset(TOL));
  }