public CurrencyAmount presentValueTheta(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      BlackVolatilitySwaptionProvider volatilityProvider) {
    ExpandedSwaption expanded = swaption.expand();
    validate(ratesProvider, expanded, volatilityProvider);
    ZonedDateTime expiryDateTime = expanded.getExpiryDateTime();
    double expiry = volatilityProvider.relativeTime(expiryDateTime);
    ExpandedSwap underlying = expanded.getUnderlying();
    ExpandedSwapLeg fixedLeg = fixedLeg(underlying);
    if (expiry < 0.0d) { // Option has expired already
      return CurrencyAmount.of(fixedLeg.getCurrency(), 0.0d);
    }
    double forward = swapPricer.parRate(underlying, ratesProvider);
    double annuityCash = swapPricer.getLegPricer().annuityCash(fixedLeg, forward);
    double discountSettle = ratesProvider.discountFactor(
        fixedLeg.getCurrency(), ((CashSettlement) expanded.getSwaptionSettlement()).getSettlementDate());
    double strike = getStrike(fixedLeg);
    double tenor = volatilityProvider.tenor(fixedLeg.getStartDate(), fixedLeg.getEndDate());
    double volatility = volatilityProvider.getVolatility(expiryDateTime, tenor, strike, forward);
    double theta = annuityCash * discountSettle
        * BlackFormulaRepository.driftlessTheta(forward, strike, expiry, volatility);
    return CurrencyAmount.of(fixedLeg.getCurrency(), theta * ((expanded.getLongShort() == LongShort.LONG) ? 1d : -1d));
  }