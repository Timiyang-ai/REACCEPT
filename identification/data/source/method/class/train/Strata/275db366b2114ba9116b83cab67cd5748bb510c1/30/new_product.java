public SwaptionSensitivity presentValueSensitivityNormalVolatility(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      NormalSwaptionVolatilities swaptionVolatilities) {

    ExpandedSwaption expanded = swaption.expand();
    validate(ratesProvider, expanded, swaptionVolatilities);
    ZonedDateTime expiryDateTime = expanded.getExpiryDateTime();
    double expiry = swaptionVolatilities.relativeTime(expiryDateTime);
    ExpandedSwap underlying = expanded.getUnderlying();
    ExpandedSwapLeg fixedLeg = fixedLeg(underlying);
    double tenor = swaptionVolatilities.tenor(fixedLeg.getStartDate(), fixedLeg.getEndDate());
    double strike = calculateStrike(fixedLeg);
    if (expiry < 0d) { // Option has expired already
      return SwaptionSensitivity.of(
          swaptionVolatilities.getConvention(), expiryDateTime, tenor, strike, 0d, fixedLeg.getCurrency(), 0d);
    }
    double forward = swapPricer.parRate(underlying, ratesProvider);
    double numeraire = calculateNumeraire(expanded, fixedLeg, forward, ratesProvider);
    double volatility = swaptionVolatilities.volatility(expiryDateTime, tenor, strike, forward);
    PutCall putCall = PutCall.ofPut(fixedLeg.getPayReceive().isReceive());
    double vega = numeraire * NormalFormulaRepository.vega(forward, strike, expiry, volatility, putCall);
    return SwaptionSensitivity.of(
        swaptionVolatilities.getConvention(),
        expiryDateTime,
        tenor,
        strike,
        forward,
        fixedLeg.getCurrency(),
        vega * expanded.getLongShort().sign());
  }