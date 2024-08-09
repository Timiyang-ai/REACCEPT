public CurrencyAmount presentValueDelta(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      NormalSwaptionVolatilities swaptionVolatilities) {

    ExpandedSwaption expanded = swaption.expand();
    validate(ratesProvider, expanded, swaptionVolatilities);
    ZonedDateTime expiryDateTime = expanded.getExpiryDateTime();
    double expiry = swaptionVolatilities.relativeTime(expiryDateTime);
    ExpandedSwap underlying = expanded.getUnderlying();
    ExpandedSwapLeg fixedLeg = fixedLeg(underlying);
    if (expiry < 0d) { // Option has expired already
      return CurrencyAmount.of(fixedLeg.getCurrency(), 0d);
    }
    double forward = swapPricer.parRate(underlying, ratesProvider);
    double numeraire = calculateNumeraire(expanded, fixedLeg, forward, ratesProvider);
    double strike = calculateStrike(fixedLeg);
    double tenor = swaptionVolatilities.tenor(fixedLeg.getStartDate(), fixedLeg.getEndDate());
    double volatility = swaptionVolatilities.volatility(expiryDateTime, tenor, strike, forward);
    PutCall putCall = PutCall.ofPut(fixedLeg.getPayReceive().isReceive());
    double delta = numeraire * NormalFormulaRepository.delta(forward, strike, expiry, volatility, putCall);
    return CurrencyAmount.of(fixedLeg.getCurrency(), delta * expanded.getLongShort().sign());
  }