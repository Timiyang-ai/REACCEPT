  @Test
  public void test_presentValue() {
    CurrencyAmount computedRec = PRICER.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    CurrencyAmount computedPay = PRICER.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    double forward = PRICER_SWAP.parRate(RSWAP_REC, RATE_PROVIDER);
    double annuityCash = PRICER_SWAP.getLegPricer().annuityCash(RFIXED_LEG_REC, forward);
    double expiry = VOLS.relativeTime(MATURITY);
    double volatility = VOLS.volatility(SWAPTION_REC_LONG.getExpiry(), TENOR_YEAR, RATE, forward);
    double df = RATE_PROVIDER.discountFactor(EUR, SETTLE);
    double expectedRec = df * annuityCash * BlackFormulaRepository.price(forward + SwaptionSabrRateVolatilityDataSet.SHIFT,
        RATE + SwaptionSabrRateVolatilityDataSet.SHIFT, expiry, volatility, false);
    double expectedPay = -df * annuityCash * BlackFormulaRepository.price(forward + SwaptionSabrRateVolatilityDataSet.SHIFT,
        RATE + SwaptionSabrRateVolatilityDataSet.SHIFT, expiry, volatility, true);
    assertThat(computedRec.getCurrency()).isEqualTo(EUR);
    assertThat(computedRec.getAmount()).isCloseTo(expectedRec, offset(NOTIONAL * TOL));
    assertThat(computedPay.getCurrency()).isEqualTo(EUR);
    assertThat(computedPay.getAmount()).isCloseTo(expectedPay, offset(NOTIONAL * TOL));
  }