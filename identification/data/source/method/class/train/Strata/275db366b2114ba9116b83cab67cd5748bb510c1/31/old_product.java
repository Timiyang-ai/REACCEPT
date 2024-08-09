public CurrencyAmount presentValueTheta(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      NormalVolatilitySwaptionProvider volatilityProvider) {
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
    boolean isCall = (fixedLeg.getPayReceive() == PayReceive.PAY);
    NormalFunctionData normalData = NormalFunctionData.of(forward, Math.abs(annuityCash * discountSettle), volatility);
    EuropeanVanillaOption option = EuropeanVanillaOption.of(strike, expiry, isCall ? PutCall.CALL : PutCall.PUT);
    double theta = NORMAL.getTheta(option, normalData) * ((expanded.getLongShort() == LongShort.LONG) ? 1.0 : -1.0);
    return CurrencyAmount.of(fixedLeg.getCurrency(), theta);
  }