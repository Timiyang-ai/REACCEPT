  @Test
  public void test_impliedVolatility() {
    double computedRec = PRICER.impliedVolatility(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    double computedPay = PRICER.impliedVolatility(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    double forward = PRICER_SWAP.parRate(RSWAP_REC, RATE_PROVIDER);
    double expected = VOLS.volatility(MATURITY, TENOR_YEAR, RATE, forward);
    assertThat(computedRec).isCloseTo(expected, offset(TOL));
    assertThat(computedPay).isCloseTo(expected, offset(TOL));
  }