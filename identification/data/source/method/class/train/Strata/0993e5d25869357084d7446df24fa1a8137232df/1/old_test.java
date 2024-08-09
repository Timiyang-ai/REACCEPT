  @Test
  public void test_vega_presentValueVega() {
    double vegaCall = PRICER.vega(CALL_OTM, RATES_PROVIDER, VOLS);
    CurrencyAmount pvVegaCall = PRICER.presentValueVega(CALL_OTM, RATES_PROVIDER, VOLS);
    double vegaPut = PRICER.vega(PUT_ITM, RATES_PROVIDER, VOLS);
    CurrencyAmount pvVegaPut = PRICER.presentValueVega(PUT_ITM, RATES_PROVIDER, VOLS);
    double timeToExpiry = VOLS.relativeTime(EXPIRY);
    double dfDom = RATES_PROVIDER.discountFactor(USD, PAYMENT_DATE);
    double forward = PRICER.getDiscountingFxSingleProductPricer().forwardFxRate(FX_PRODUCT_HIGH, RATES_PROVIDER)
        .fxRate(CURRENCY_PAIR);
    double vol = SMILE_TERM.volatility(timeToExpiry, STRIKE_RATE_HIGH, forward);
    double expectedVega = dfDom * BlackFormulaRepository.vega(forward, STRIKE_RATE_HIGH, timeToExpiry, vol);
    double expectedPvVega = -NOTIONAL * dfDom *
        BlackFormulaRepository.vega(forward, STRIKE_RATE_HIGH, timeToExpiry, vol);
    assertThat(vegaCall).isCloseTo(expectedVega, offset(TOL));
    assertThat(pvVegaCall.getCurrency()).isEqualTo(USD);
    assertThat(pvVegaCall.getAmount()).isCloseTo(expectedPvVega, offset(NOTIONAL * TOL));
    assertThat(vegaPut).isCloseTo(expectedVega, offset(TOL));
    assertThat(pvVegaPut.getCurrency()).isEqualTo(USD);
    assertThat(pvVegaPut.getAmount()).isCloseTo(-expectedPvVega, offset(NOTIONAL * TOL));
  }