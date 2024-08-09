  @Test
  public void test_theta_presentValueTheta() {
    double theta = PRICER.theta(CALL_OTM, RATES_PROVIDER, VOLS);
    CurrencyAmount pvTheta = PRICER.presentValueTheta(CALL_OTM, RATES_PROVIDER, VOLS);
    double timeToExpiry = VOLS.relativeTime(EXPIRY);
    double dfDom = RATES_PROVIDER.discountFactor(USD, PAYMENT_DATE);
    double forward = PRICER.getDiscountingFxSingleProductPricer().forwardFxRate(FX_PRODUCT_HIGH, RATES_PROVIDER)
        .fxRate(CURRENCY_PAIR);
    double vol = SMILE_TERM.volatility(timeToExpiry, STRIKE_RATE_HIGH, forward);
    double expectedTheta = dfDom * BlackFormulaRepository.driftlessTheta(forward, STRIKE_RATE_HIGH, timeToExpiry, vol);
    assertThat(theta).isCloseTo(expectedTheta, offset(TOL));
    double expectedPvTheta = -NOTIONAL * dfDom *
        BlackFormulaRepository.driftlessTheta(forward, STRIKE_RATE_HIGH, timeToExpiry, vol);
    assertThat(pvTheta.getCurrency()).isEqualTo(USD);
    assertThat(pvTheta.getAmount()).isCloseTo(expectedPvTheta, offset(NOTIONAL * TOL));
  }