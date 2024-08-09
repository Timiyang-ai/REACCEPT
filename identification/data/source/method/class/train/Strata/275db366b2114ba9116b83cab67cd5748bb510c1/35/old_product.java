public CurrencyAmount presentValue(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      SabrVolatilitySwaptionProvider volatilityProvider) {

    ExpandedSwaption expanded = swaption.expand();
    validate(ratesProvider, expanded, volatilityProvider);
    ZonedDateTime expiryDateTime = expanded.getExpiryDateTime();
    double expiry = volatilityProvider.relativeTime(expiryDateTime);
    ExpandedSwap underlying = expanded.getUnderlying();
    ExpandedSwapLeg fixedLeg = fixedLeg(underlying);
    if (expiry < 0d) { // Option has expired already
      return CurrencyAmount.of(fixedLeg.getCurrency(), 0d);
    }
    double tenor = volatilityProvider.tenor(fixedLeg.getStartDate(), fixedLeg.getEndDate());
    double shift = volatilityProvider.getParameters().getShift(DoublesPair.of(expiry, tenor));
    double forward = swapPricer.parRate(underlying, ratesProvider);
    double pvbp = swapPricer.getLegPricer().pvbp(fixedLeg, ratesProvider);
    double strike = swapPricer.getLegPricer().couponEquivalent(fixedLeg, ratesProvider, pvbp);
    double volatility = volatilityProvider.getVolatility(expiryDateTime, tenor, strike, forward);
    boolean isCall = (fixedLeg.getPayReceive() == PayReceive.PAY);
    // Payer at strike is exercise when rate > strike, i.e. call on rate
    double price =
        Math.abs(pvbp) * BlackFormulaRepository.price(forward + shift, strike + shift, expiry, volatility, isCall);
    double pv = price * ((expanded.getLongShort() == LongShort.LONG) ? 1d : -1d);
    return CurrencyAmount.of(fixedLeg.getCurrency(), pv);
  }