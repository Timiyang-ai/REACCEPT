public PointSensitivityBuilder presentValueSensitivityModelParamsSabr(
      ResolvedSwaption swaption,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    validate(swaption, ratesProvider, swaptionVolatilities);
    ZonedDateTime expiryDateTime = swaption.getExpiry();
    double expiry = swaptionVolatilities.relativeTime(expiryDateTime);
    ResolvedSwap underlying = swaption.getUnderlying();
    ResolvedSwapLeg fixedLeg = fixedLeg(underlying);
    double tenor = swaptionVolatilities.tenor(fixedLeg.getStartDate(), fixedLeg.getEndDate());
    double shift = swaptionVolatilities.shift(expiry, tenor);
    double pvbp = getSwapPricer().getLegPricer().pvbp(fixedLeg, ratesProvider);
    double strike = getSwapPricer().getLegPricer().couponEquivalent(fixedLeg, ratesProvider, pvbp);
    if (expiry < 0d) { // Option has expired already
      return PointSensitivityBuilder.none();
    }
    double forward = getSwapPricer().parRate(underlying, ratesProvider);
    double volatility = swaptionVolatilities.volatility(expiryDateTime, tenor, strike, forward);
    DoubleArray derivative =
        swaptionVolatilities.volatilityAdjoint(expiry, tenor, strike, forward).getDerivatives();
    // Backward sweep
    double vega = Math.abs(pvbp) * BlackFormulaRepository.vega(forward + shift, strike + shift, expiry, volatility)
        * swaption.getLongShort().sign();
    // sensitivities
    FixedIborSwapConvention conv = swaptionVolatilities.getConvention();
    Currency ccy = fixedLeg.getCurrency();
    return PointSensitivityBuilder.of(
        SwaptionSabrSensitivity.of(conv, expiryDateTime, tenor, ALPHA, ccy, vega * derivative.get(2)),
        SwaptionSabrSensitivity.of(conv, expiryDateTime, tenor, BETA, ccy, vega * derivative.get(3)),
        SwaptionSabrSensitivity.of(conv, expiryDateTime, tenor, RHO, ccy, vega * derivative.get(4)),
        SwaptionSabrSensitivity.of(conv, expiryDateTime, tenor, NU, ccy, vega * derivative.get(5)));
  }