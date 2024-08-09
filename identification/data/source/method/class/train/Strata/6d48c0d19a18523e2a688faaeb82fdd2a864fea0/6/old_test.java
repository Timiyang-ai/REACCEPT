  @Test
  public void test_vega_presentValueSensitivityVolatility() {
    double computedVegaCall = PRICER.vega(CALL_UKI, RATE_PROVIDER, VOLS);
    FxOptionSensitivity computedCall =
        (FxOptionSensitivity) PRICER.presentValueSensitivityModelParamsVolatility(CALL_UKI, RATE_PROVIDER, VOLS);
    double computedVegaPut = PRICER.vega(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    FxOptionSensitivity computedPut =
        (FxOptionSensitivity) PRICER.presentValueSensitivityModelParamsVolatility(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    double rateBase = RATE_PROVIDER.discountFactors(EUR).zeroRate(PAY_DATE);
    double rateCounter = RATE_PROVIDER.discountFactors(USD).zeroRate(PAY_DATE);
    double costOfCarry = rateCounter - rateBase;
    double forward = RATE_PROVIDER.fxForwardRates(CURRENCY_PAIR).rate(EUR, PAY_DATE);
    double volatility = VOLS.volatility(CURRENCY_PAIR, EXPIRY_DATETIME, STRIKE_RATE, forward);
    double timeToExpiry = VOLS.relativeTime(EXPIRY_DATETIME);
    double rebateRate = REBATE_AMOUNT / NOTIONAL;
    double expectedCash = CASH_REBATE_PRICER
        .priceAdjoint(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKO).getDerivative(3);
    double expectedAsset = ASSET_REBATE_PRICER
        .priceAdjoint(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKI).getDerivative(3);
    double expectedCall = BARRIER_PRICER.priceAdjoint(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, true, BARRIER_UKI).getDerivative(4) + rebateRate * expectedCash;
    double expectedPut = BARRIER_PRICER.priceAdjoint(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, false, BARRIER_UKO).getDerivative(4) + rebateRate * expectedAsset;
    assertThat(computedVegaCall).isCloseTo(expectedCall, offset(TOL));
    assertThat(computedCall.getSensitivity()).isCloseTo(expectedCall * NOTIONAL, offset(TOL * NOTIONAL));
    assertThat(computedCall.getCurrency()).isEqualTo(USD);
    assertThat(computedCall.getCurrencyPair()).isEqualTo(CURRENCY_PAIR);
    assertThat(computedCall.getStrike()).isEqualTo(STRIKE_RATE);
    assertThat(computedCall.getForward()).isCloseTo(forward, offset(TOL));
    assertThat(computedCall.getExpiry()).isEqualTo(timeToExpiry);
    assertThat(computedVegaPut).isCloseTo(expectedPut, offset(TOL));
    assertThat(computedPut.getSensitivity()).isCloseTo(-expectedPut * NOTIONAL, offset(TOL * NOTIONAL));
    assertThat(computedPut.getCurrency()).isEqualTo(USD);
    assertThat(computedPut.getCurrencyPair()).isEqualTo(CURRENCY_PAIR);
    assertThat(computedPut.getStrike()).isEqualTo(STRIKE_RATE);
    assertThat(computedPut.getForward()).isCloseTo(forward, offset(TOL));
    assertThat(computedPut.getExpiry()).isEqualTo(timeToExpiry);
  }