  @Test
  public void test_delta_presentValueDelta() {
    double deltaCall = PRICER.delta(CALL_OTM, RATES_PROVIDER, VOLS);
    CurrencyAmount pvDeltaCall = PRICER.presentValueDelta(CALL_OTM, RATES_PROVIDER, VOLS);
    double deltaPut = PRICER.delta(PUT_ITM, RATES_PROVIDER, VOLS);
    CurrencyAmount pvDeltaPut = PRICER.presentValueDelta(PUT_ITM, RATES_PROVIDER, VOLS);
    double timeToExpiry = VOLS.relativeTime(EXPIRY);
    double dfFor = RATES_PROVIDER.discountFactor(EUR, PAYMENT_DATE);
    double forward = PRICER.getDiscountingFxSingleProductPricer().forwardFxRate(FX_PRODUCT_HIGH, RATES_PROVIDER)
        .fxRate(CURRENCY_PAIR);
    double vol = SMILE_TERM.volatility(timeToExpiry, STRIKE_RATE_HIGH, forward);
    double expectedDeltaCall = dfFor * BlackFormulaRepository.delta(forward, STRIKE_RATE_HIGH, timeToExpiry, vol, true);
    double expectedDeltaPut = dfFor * BlackFormulaRepository.delta(forward, STRIKE_RATE_HIGH, timeToExpiry, vol, false);
    double expectedPvDeltaCall = -NOTIONAL * dfFor
        * BlackFormulaRepository.delta(forward, STRIKE_RATE_HIGH, timeToExpiry, vol, true);
    double expectedPvDeltaPut = NOTIONAL * dfFor
        * BlackFormulaRepository.delta(forward, STRIKE_RATE_HIGH, timeToExpiry, vol, false);
    assertThat(deltaCall).isCloseTo(expectedDeltaCall, offset(TOL));
    assertThat(pvDeltaCall.getCurrency()).isEqualTo(USD);
    assertThat(pvDeltaCall.getAmount()).isCloseTo(expectedPvDeltaCall, offset(NOTIONAL * TOL));
    assertThat(deltaPut).isCloseTo(expectedDeltaPut, offset(TOL));
    assertThat(pvDeltaPut.getCurrency()).isEqualTo(USD);
    assertThat(pvDeltaPut.getAmount()).isCloseTo(expectedPvDeltaPut, offset(NOTIONAL * TOL));
  }