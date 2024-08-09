  @Test
  public void test_gamma_presentValueGamma() {
    double gammaCall = PRICER.gamma(CALL_OTM, RATES_PROVIDER, VOLS);
    CurrencyAmount pvGammaCall = PRICER.presentValueGamma(CALL_OTM, RATES_PROVIDER, VOLS);
    double gammaPut = PRICER.gamma(PUT_ITM, RATES_PROVIDER, VOLS);
    CurrencyAmount pvGammaPut = PRICER.presentValueGamma(PUT_ITM, RATES_PROVIDER, VOLS);
    double timeToExpiry = VOLS.relativeTime(EXPIRY);
    double dfDom = RATES_PROVIDER.discountFactor(USD, PAYMENT_DATE);
    double dfFor = RATES_PROVIDER.discountFactor(EUR, PAYMENT_DATE);
    double forward = PRICER.getDiscountingFxSingleProductPricer().forwardFxRate(FX_PRODUCT_HIGH, RATES_PROVIDER)
        .fxRate(CURRENCY_PAIR);
    double vol = SMILE_TERM.volatility(timeToExpiry, STRIKE_RATE_HIGH, forward);
    double expectedGamma = dfFor * dfFor / dfDom *
        BlackFormulaRepository.gamma(forward, STRIKE_RATE_HIGH, timeToExpiry, vol);
    double expectedPvGamma = -NOTIONAL * dfFor * dfFor / dfDom *
        BlackFormulaRepository.gamma(forward, STRIKE_RATE_HIGH, timeToExpiry, vol);
    assertThat(gammaCall).isCloseTo(expectedGamma, offset(TOL));
    assertThat(pvGammaCall.getCurrency()).isEqualTo(USD);
    assertThat(pvGammaCall.getAmount()).isCloseTo(expectedPvGamma, offset(NOTIONAL * TOL));
    assertThat(gammaPut).isCloseTo(expectedGamma, offset(TOL));
    assertThat(pvGammaPut.getCurrency()).isEqualTo(USD);
    assertThat(pvGammaPut.getAmount()).isCloseTo(-expectedPvGamma, offset(NOTIONAL * TOL));
  }