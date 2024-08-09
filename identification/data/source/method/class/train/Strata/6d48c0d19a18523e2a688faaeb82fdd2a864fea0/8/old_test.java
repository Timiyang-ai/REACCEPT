  @Test
  public void test_theta_presentValueTheta() {
    double computedThetaCall = PRICER.theta(CALL_UKI, RATE_PROVIDER, VOLS);
    double computedThetaPut = PRICER.theta(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPvThetaCall = PRICER.presentValueTheta(CALL_UKI, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPvThetaPut = PRICER.presentValueTheta(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    double rateBase = RATE_PROVIDER.discountFactors(EUR).zeroRate(PAY_DATE);
    double rateCounter = RATE_PROVIDER.discountFactors(USD).zeroRate(PAY_DATE);
    double costOfCarry = rateCounter - rateBase;
    double forward = RATE_PROVIDER.fxForwardRates(CURRENCY_PAIR).rate(EUR, PAY_DATE);
    double volatility = VOLS.volatility(CURRENCY_PAIR, EXPIRY_DATETIME, STRIKE_RATE, forward);
    double timeToExpiry = VOLS.relativeTime(EXPIRY_DATETIME);
    double rebateRate = REBATE_AMOUNT / NOTIONAL;
    double expectedCash = CASH_REBATE_PRICER
        .priceAdjoint(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKO).getDerivative(4);
    double expectedAsset = ASSET_REBATE_PRICER
        .priceAdjoint(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKI).getDerivative(4);
    double expectedThetaCall = BARRIER_PRICER.priceAdjoint(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, true, BARRIER_UKI).getDerivative(5) + rebateRate * expectedCash;
    double expectedThetaPut = BARRIER_PRICER.priceAdjoint(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, false, BARRIER_UKO).getDerivative(5) + rebateRate * expectedAsset;
    expectedThetaCall *= -1d;
    expectedThetaPut *= -1d;
    assertThat(computedThetaCall).isCloseTo(expectedThetaCall, offset(TOL));
    assertThat(computedThetaPut).isCloseTo(expectedThetaPut, offset(TOL));
    assertThat(computedPvThetaCall.getCurrency()).isEqualTo(USD);
    assertThat(computedPvThetaPut.getCurrency()).isEqualTo(USD);
    assertThat(computedPvThetaCall.getAmount()).isCloseTo(expectedThetaCall * NOTIONAL, offset(TOL));
    assertThat(computedPvThetaPut.getAmount()).isCloseTo(-expectedThetaPut * NOTIONAL, offset(TOL));
  }