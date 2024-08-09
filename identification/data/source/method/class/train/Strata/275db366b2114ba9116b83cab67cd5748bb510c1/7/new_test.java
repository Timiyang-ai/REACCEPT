  @Test
  public void test_impliedVolatility() {
    double computedRec = SWAPTION_PRICER.impliedVolatility(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    double computedPay = SWAPTION_PRICER.impliedVolatility(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    double forward = SWAP_PRICER.parRate(RSWAP_REC, RATE_PROVIDER);
    double expected = VOLS.volatility(MATURITY_DATE, TENOR_YEAR, RATE, forward);
    assertThat(computedRec).isCloseTo(expected, offset(TOL));
    assertThat(computedPay).isCloseTo(expected, offset(TOL));
  }