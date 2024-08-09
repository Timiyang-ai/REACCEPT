  @Test
  public void test_price_presentValue() {
    double computedPriceCall = PRICER.price(CALL_UKI, RATE_PROVIDER, VOLS);
    double computedPricePut = PRICER.price(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPvCall = PRICER.presentValue(CALL_UKI, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPvPut = PRICER.presentValue(PUT_UKO_BASE, RATE_PROVIDER, VOLS);
    double rateBase = RATE_PROVIDER.discountFactors(EUR).zeroRate(PAY_DATE);
    double rateCounter = RATE_PROVIDER.discountFactors(USD).zeroRate(PAY_DATE);
    double costOfCarry = rateCounter - rateBase;
    double forward = RATE_PROVIDER.fxForwardRates(CURRENCY_PAIR).rate(EUR, PAY_DATE);
    double volatility = VOLS.volatility(CURRENCY_PAIR, EXPIRY_DATETIME, STRIKE_RATE, forward);
    double timeToExpiry = VOLS.relativeTime(EXPIRY_DATETIME);
    double rebateRate = REBATE_AMOUNT / NOTIONAL;
    double expectedCash =
        CASH_REBATE_PRICER.price(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKO);
    double expectedAsset =
        ASSET_REBATE_PRICER.price(SPOT, timeToExpiry, costOfCarry, rateCounter, volatility, BARRIER_UKI);
    double expectedPriceCall = BARRIER_PRICER.price(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, true, BARRIER_UKI) + rebateRate * expectedCash;
    double expectedPricePut = BARRIER_PRICER.price(SPOT, STRIKE_RATE, timeToExpiry, costOfCarry, rateCounter,
        volatility, false, BARRIER_UKO) + rebateRate * expectedAsset;
    assertThat(computedPriceCall).isCloseTo(expectedPriceCall, offset(TOL));
    assertThat(computedPricePut).isCloseTo(expectedPricePut, offset(TOL));
    assertThat(computedPvCall.getCurrency()).isEqualTo(USD);
    assertThat(computedPvPut.getCurrency()).isEqualTo(USD);
    assertThat(computedPvCall.getAmount()).isCloseTo(expectedPriceCall * NOTIONAL, offset(TOL));
    assertThat(computedPvPut.getAmount()).isCloseTo(-expectedPricePut * NOTIONAL, offset(TOL));
  }