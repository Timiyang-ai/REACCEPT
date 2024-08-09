public SwaptionSensitivity presentValueSensitivityBlackVolatility(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      BlackVolatilitySwaptionProvider volatilityProvider) {
    ExpandedSwaption expanded = swaption.expand();
    validate(ratesProvider, expanded, volatilityProvider);
    ZonedDateTime expiryDateTime = expanded.getExpiryDateTime();
    double expiry = volatilityProvider.relativeTime(expiryDateTime);
    ExpandedSwap underlying = expanded.getUnderlying();
    ExpandedSwapLeg fixedLeg = fixedLeg(underlying);
    double tenor = volatilityProvider.tenor(fixedLeg.getStartDate(), fixedLeg.getEndDate());
    double strike = getStrike(fixedLeg);
    if (expiry < 0.0d) { // Option has expired already
      return SwaptionSensitivity.of(volatilityProvider.getConvention(), expiryDateTime, tenor, strike, 0.0d,
          fixedLeg.getCurrency(), 0.0d);
    }
    double forward = swapPricer.parRate(underlying, ratesProvider);
    double annuityCash = swapPricer.getLegPricer().annuityCash(fixedLeg, forward);
    double discountSettle = ratesProvider.discountFactor(
        fixedLeg.getCurrency(), ((CashSettlement) expanded.getSwaptionSettlement()).getSettlementDate());
    double volatility = volatilityProvider.getVolatility(expiryDateTime, tenor, strike, forward);
    double vega = annuityCash * discountSettle * BlackFormulaRepository.vega(forward, strike, expiry, volatility)
        * ((expanded.getLongShort() == LongShort.LONG) ? 1.0 : -1.0);
    return SwaptionSensitivity.of(
        volatilityProvider.getConvention(), expiryDateTime, tenor, strike, forward, fixedLeg.getCurrency(), vega);
  }