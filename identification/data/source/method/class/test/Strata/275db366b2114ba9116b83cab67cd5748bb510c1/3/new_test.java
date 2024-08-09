  @Test
  public void test_presentValueSensitivityBlackVolatility() {
    SwaptionSensitivity sensiRec =
        PRICER.presentValueSensitivityModelParamsVolatility(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    SwaptionSensitivity sensiPay =
        PRICER.presentValueSensitivityModelParamsVolatility(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    double forward = SWAP_PRICER.parRate(RSWAP_REC, RATE_PROVIDER);
    double annuityCash = SWAP_PRICER.getLegPricer().annuityCash(RFIXED_LEG_REC, forward);
    double expiry = VOLS.relativeTime(SWAPTION_REC_LONG.getExpiry());
    double tenor = VOLS.tenor(SETTLE, END);
    double volatility = SURFACE.zValue(expiry, tenor);
    double settle = ACT_ACT_ISDA.relativeYearFraction(VAL_DATE, SETTLE);
    double df = Math.exp(-DSC_CURVE.yValue(settle) * settle);
    double expectedRec = df * annuityCash * BlackFormulaRepository.vega(forward, RATE, expiry, volatility);
    double expectedPay = -df * annuityCash * BlackFormulaRepository.vega(forward, RATE, expiry, volatility);
    assertThat(sensiRec.getCurrency()).isEqualTo(EUR);
    assertThat(sensiRec.getSensitivity()).isCloseTo(expectedRec, offset(NOTIONAL * TOL));
    assertThat(sensiRec.getVolatilitiesName()).isEqualTo(VOLS.getName());
    assertThat(sensiRec.getExpiry()).isEqualTo(expiry);
    assertThat(sensiRec.getTenor()).isEqualTo(5.0);
    assertThat(sensiRec.getStrike()).isEqualTo(RATE);
    assertThat(sensiRec.getForward()).isCloseTo(forward, offset(TOL));
    assertThat(sensiPay.getCurrency()).isEqualTo(EUR);
    assertThat(sensiPay.getSensitivity()).isCloseTo(expectedPay, offset(NOTIONAL * TOL));
    assertThat(sensiRec.getVolatilitiesName()).isEqualTo(VOLS.getName());
    assertThat(sensiPay.getExpiry()).isEqualTo(expiry);
    assertThat(sensiPay.getTenor()).isEqualTo(5.0);
    assertThat(sensiPay.getStrike()).isEqualTo(RATE);
    assertThat(sensiPay.getForward()).isCloseTo(forward, offset(TOL));
  }