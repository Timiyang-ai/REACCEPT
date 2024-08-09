public PointSensitivityBuilder presentValueSensitivity(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    ExpandedSwaption expanded = swaption.expand();
    validate(expanded, ratesProvider, swaptionVolatilities);
    ZonedDateTime expiryDateTime = expanded.getExpiryDateTime();
    double expiry = swaptionVolatilities.relativeTime(expiryDateTime);
    ResolvedSwap underlying = expanded.getUnderlying();
    ResolvedSwapLeg fixedLeg = fixedLeg(underlying);
    if (expiry < 0d) { // Option has expired already
      return PointSensitivityBuilder.none();
    }
    double forward = getSwapPricer().parRate(underlying, ratesProvider);
    double pvbp = getSwapPricer().getLegPricer().pvbp(fixedLeg, ratesProvider);
    double strike = getSwapPricer().getLegPricer().couponEquivalent(fixedLeg, ratesProvider, pvbp);
    double tenor = swaptionVolatilities.tenor(fixedLeg.getStartDate(), fixedLeg.getEndDate());
    double shift = swaptionVolatilities.shift(expiry, tenor);
    ValueDerivatives volatilityAdj = swaptionVolatilities.volatilityAdjoint(expiry, tenor, strike, forward);
    boolean isCall = fixedLeg.getPayReceive().isPay();
    // Payer at strike is exercise when rate > strike, i.e. call on rate
    // Backward sweep
    PointSensitivityBuilder pvbpDr = getSwapPricer().getLegPricer().pvbpSensitivity(fixedLeg, ratesProvider);
    PointSensitivityBuilder forwardDr = getSwapPricer().parRateSensitivity(underlying, ratesProvider);
    double shiftedForward = forward + shift;
    double shiftedStrike = strike + shift;
    double price = BlackFormulaRepository.price(shiftedForward, shiftedStrike, expiry, volatilityAdj.getValue(), isCall);
    double delta = BlackFormulaRepository.delta(shiftedForward, shiftedStrike, expiry, volatilityAdj.getValue(), isCall);
    double vega = BlackFormulaRepository.vega(shiftedForward, shiftedStrike, expiry, volatilityAdj.getValue());
    double sign = expanded.getLongShort().sign();
    return pvbpDr.multipliedBy(price * sign * Math.signum(pvbp))
        .combinedWith(forwardDr.multipliedBy((delta + vega * volatilityAdj.getDerivative(0)) * Math.abs(pvbp) * sign));
  }