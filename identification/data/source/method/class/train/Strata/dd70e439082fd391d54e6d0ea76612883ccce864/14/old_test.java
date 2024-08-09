  @Test
  public void test_price_presentValue() {
    double priceCallOtm = PRICER.price(CALL_OTM, RATES_PROVIDER, VOLS);
    CurrencyAmount pvCallOtm = PRICER.presentValue(CALL_OTM, RATES_PROVIDER, VOLS);
    double pricePutOtm = PRICER.price(PUT_OTM, RATES_PROVIDER, VOLS);
    CurrencyAmount pvPutOtm = PRICER.presentValue(PUT_OTM, RATES_PROVIDER, VOLS);
    double timeToExpiry = VOLS.relativeTime(EXPIRY);
    double df = RATES_PROVIDER.discountFactor(USD, PAYMENT_DATE);
    double forward = PRICER.getDiscountingFxSingleProductPricer()
        .forwardFxRate(FX_PRODUCT_HIGH, RATES_PROVIDER).fxRate(CURRENCY_PAIR);
    double volHigh = SMILE_TERM.volatility(timeToExpiry, STRIKE_RATE_HIGH, forward);
    double volLow = SMILE_TERM.volatility(timeToExpiry, STRIKE_RATE_LOW, forward);
    double expectedPriceCallOtm =
        df * BlackFormulaRepository.price(forward, STRIKE_RATE_HIGH, timeToExpiry, volHigh, true);
    double expectedPricePutOtm =
        df * BlackFormulaRepository.price(forward, STRIKE_RATE_LOW, timeToExpiry, volLow, false);
    double expectedPvCallOtm = -NOTIONAL * df *
        BlackFormulaRepository.price(forward, STRIKE_RATE_HIGH, timeToExpiry, volHigh, true);
    double expectedPvPutOtm = -NOTIONAL * df *
        BlackFormulaRepository.price(forward, STRIKE_RATE_LOW, timeToExpiry, volLow, false);
    assertThat(priceCallOtm).isCloseTo(expectedPriceCallOtm, offset(TOL));
    assertThat(pvCallOtm.getCurrency()).isEqualTo(USD);
    assertThat(pvCallOtm.getAmount()).isCloseTo(expectedPvCallOtm, offset(NOTIONAL * TOL));
    assertThat(pricePutOtm).isCloseTo(expectedPricePutOtm, offset(TOL));
    assertThat(pvPutOtm.getCurrency()).isEqualTo(USD);
    assertThat(pvPutOtm.getAmount()).isCloseTo(expectedPvPutOtm, offset(NOTIONAL * TOL));
  }