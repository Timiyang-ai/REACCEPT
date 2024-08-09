public PointSensitivityBuilder presentValueSensitivityModelParamsSabr(
      ResolvedSwaption swaption,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    validate(swaption, ratesProvider, swaptionVolatilities);
    double expiry = swaptionVolatilities.relativeTime(swaption.getExpiry());
    ResolvedSwap underlying = swaption.getUnderlying();
    ResolvedSwapLeg fixedLeg = fixedLeg(underlying);
    double tenor = swaptionVolatilities.tenor(fixedLeg.getStartDate(), fixedLeg.getEndDate());
    double shift = swaptionVolatilities.shift(expiry, tenor);
    double strike = calculateStrike(fixedLeg);
    if (expiry < 0d) { // Option has expired already
      return PointSensitivityBuilder.none();
    }
    double forward = getSwapPricer().parRate(underlying, ratesProvider);
    double volatility = swaptionVolatilities.volatility(expiry, tenor, strike, forward);
    double numeraire = calculateNumeraire(swaption, fixedLeg, forward, ratesProvider);
    DoubleArray derivative = swaptionVolatilities.volatilityAdjoint(expiry, tenor, strike, forward).getDerivatives();
    double vega = numeraire * swaption.getLongShort().sign() *
        BlackFormulaRepository.vega(forward + shift, strike + shift, expiry, volatility);
    // sensitivities
    Currency ccy = fixedLeg.getCurrency();
    SwaptionVolatilitiesName name = swaptionVolatilities.getName();
    return PointSensitivityBuilder.of(
        SwaptionSabrSensitivity.of(name, expiry, tenor, ALPHA, ccy, vega * derivative.get(2)),
        SwaptionSabrSensitivity.of(name, expiry, tenor, BETA, ccy, vega * derivative.get(3)),
        SwaptionSabrSensitivity.of(name, expiry, tenor, RHO, ccy, vega * derivative.get(4)),
        SwaptionSabrSensitivity.of(name, expiry, tenor, NU, ccy, vega * derivative.get(5)));
  }