  @Test
  public void test_impliedVolatility() {
    double forward = PRICER_SWAP.parRate(RSWAP_REC, RATE_PROVIDER);
    double expected = VOLS.volatility(SWAPTION_REC_LONG.getExpiry(), SWAP_TENOR_YEAR, STRIKE, forward);
    double computedRec = PRICER_SWAPTION.impliedVolatility(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    double computedPay = PRICER_SWAPTION.impliedVolatility(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    assertThat(computedRec).isCloseTo(expected, offset(TOL));
    assertThat(computedPay).isCloseTo(expected, offset(TOL));
  }