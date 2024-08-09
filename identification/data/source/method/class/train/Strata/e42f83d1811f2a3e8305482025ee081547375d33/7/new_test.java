  @Test
  public void test_presentValueSensitivityBlackVolatility() {
    FxOptionSensitivity computedCall = (FxOptionSensitivity)
        PRICER.presentValueSensitivityModelParamsVolatility(CALL_OTM, RATES_PROVIDER, VOLS);
    FxOptionSensitivity computedPut = (FxOptionSensitivity)
        PRICER.presentValueSensitivityModelParamsVolatility(PUT_ITM, RATES_PROVIDER, VOLS);
    double timeToExpiry = VOLS.relativeTime(EXPIRY);
    double df = RATES_PROVIDER.discountFactor(USD, PAYMENT_DATE);
    double forward = PRICER.getDiscountingFxSingleProductPricer().forwardFxRate(FX_PRODUCT_HIGH, RATES_PROVIDER)
        .fxRate(CURRENCY_PAIR);
    double vol = SMILE_TERM.volatility(timeToExpiry, STRIKE_RATE_HIGH, forward);
    FxOptionSensitivity expected = FxOptionSensitivity.of(
        VOLS.getName(), CURRENCY_PAIR, timeToExpiry, STRIKE_RATE_HIGH, forward, USD,
        -NOTIONAL * df * BlackFormulaRepository.vega(forward, STRIKE_RATE_HIGH, timeToExpiry, vol));
    assertThat(computedCall.build().equalWithTolerance(expected.build(), NOTIONAL * TOL)).isTrue();
    assertThat(computedPut.build().equalWithTolerance(expected.build().multipliedBy(-1d), NOTIONAL * TOL)).isTrue();
  }