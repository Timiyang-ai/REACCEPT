  @Test
  public void test_presentValueDelta() {
    CurrencyAmount computedRec = PRICER.presentValueDelta(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPay = PRICER.presentValueDelta(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    double forward = SWAP_PRICER.parRate(RSWAP_REC, RATE_PROVIDER);
    double annuityCash = SWAP_PRICER.getLegPricer().annuityCash(RFIXED_LEG_REC, forward);
    double expiry = VOLS.relativeTime(SWAPTION_REC_LONG.getExpiry());
    double tenor = VOLS.tenor(SETTLE, END);
    double volatility = SURFACE.zValue(expiry, tenor);
    double settle = ACT_ACT_ISDA.relativeYearFraction(VAL_DATE, SETTLE);
    double df = Math.exp(-DSC_CURVE.yValue(settle) * settle);
    double expectedRec = df * annuityCash * BlackFormulaRepository.delta(forward, RATE, expiry, volatility, false);
    double expectedPay = -df * annuityCash * BlackFormulaRepository.delta(forward, RATE, expiry, volatility, true);
    assertThat(computedRec.getCurrency()).isEqualTo(EUR);
    assertThat(computedRec.getAmount()).isCloseTo(expectedRec, offset(NOTIONAL * TOL));
    assertThat(computedPay.getCurrency()).isEqualTo(EUR);
    assertThat(computedPay.getAmount()).isCloseTo(expectedPay, offset(NOTIONAL * TOL));
  }