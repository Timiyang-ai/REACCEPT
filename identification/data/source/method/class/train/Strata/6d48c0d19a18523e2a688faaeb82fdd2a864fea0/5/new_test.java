  @Test
  public void test_gamma_presentValueGamma() {
    double computedGammaCall = PRICER.gamma(CALL_UKI, RATE_PROVIDER, VOLS);
    double computedGammaPut = PRICER.gamma(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPvGammaCall = PRICER.presentValueGamma(CALL_UKI, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPvGammaPut = PRICER.presentValueGamma(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    double rateBase = RATE_PROVIDER.discountFactors(EUR).zeroRate(PAY_DATE);
    double rateCounter = RATE_PROVIDER.discountFactors(USD).zeroRate(PAY_DATE);
    double costOfCarry = rateCounter - rateBase;
    double forward = RATE_PROVIDER.fxForwardRates(CURRENCY_PAIR).rate(EUR, PAY_DATE);
    double volatility = VOLS.volatility(CURRENCY_PAIR, EXPIRY_DATETIME, STRIKE_RATE, forward);
    double timeToExpiry = VOLS.relativeTime(EXPIRY_DATETIME);
    double rebateRate = REBATE_AMOUNT / NOTIONAL;
    double expectedCash = CASH_REBATE_PRICER
        .priceAdjoint(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKO).getDerivative(5);
    double expectedAsset = ASSET_REBATE_PRICER
        .priceAdjoint(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKI).getDerivative(5);
    double expectedGammaCall = BARRIER_PRICER.priceAdjoint(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, true, BARRIER_UKI).getDerivative(6) + rebateRate * expectedCash;
    double expectedGammaPut = BARRIER_PRICER.priceAdjoint(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, false, BARRIER_UKO).getDerivative(6) + rebateRate * expectedAsset;
    assertThat(computedGammaCall).isCloseTo(expectedGammaCall, offset(TOL));
    assertThat(computedGammaPut).isCloseTo(expectedGammaPut, offset(TOL));
    assertThat(computedPvGammaCall.getCurrency()).isEqualTo(USD);
    assertThat(computedPvGammaPut.getCurrency()).isEqualTo(USD);
    assertThat(computedPvGammaCall.getAmount()).isCloseTo(expectedGammaCall * NOTIONAL, offset(TOL));
    assertThat(computedPvGammaPut.getAmount()).isCloseTo(-expectedGammaPut * NOTIONAL, offset(TOL));
  }