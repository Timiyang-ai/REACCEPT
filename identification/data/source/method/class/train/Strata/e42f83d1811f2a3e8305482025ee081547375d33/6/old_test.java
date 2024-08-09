  @Test
  public void test_impliedVolatility() {
    double computedCall = PRICER.impliedVolatility(CALL_OTM, RATES_PROVIDER, VOLS);
    double computedPut = PRICER.impliedVolatility(PUT_ITM, RATES_PROVIDER, VOLS);
    double timeToExpiry = VOLS.relativeTime(EXPIRY);
    double forward = PRICER.getDiscountingFxSingleProductPricer().forwardFxRate(FX_PRODUCT_HIGH, RATES_PROVIDER)
        .fxRate(CURRENCY_PAIR);
    double expected = SMILE_TERM.volatility(timeToExpiry, STRIKE_RATE_HIGH, forward);
    assertThat(computedCall).isEqualTo(expected);
    assertThat(computedPut).isEqualTo(expected);
  }