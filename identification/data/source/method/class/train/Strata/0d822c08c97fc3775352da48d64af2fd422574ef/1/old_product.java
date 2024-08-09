public PointSensitivityBuilder presentValueSensitivityRatesStickyModel(
      ResolvedSwaption swaption,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    validate(swaption, ratesProvider, swaptionVolatilities);
    ZonedDateTime expiryDateTime = swaption.getExpiry();
    double expiry = swaptionVolatilities.relativeTime(expiryDateTime);
    ResolvedSwap underlying = swaption.getUnderlying();
    ResolvedSwapLeg fixedLeg = fixedLeg(underlying);
    if (expiry < 0d) { // Option has expired already
      return PointSensitivityBuilder.none();
    }
    double forward = getSwapPricer().parRate(underlying, ratesProvider);
    ValueDerivatives annuityDerivative = getSwapPricer().getLegPricer().annuityCashDerivative(fixedLeg, forward);
    double annuityCash = annuityDerivative.getValue();
    double annuityCashDr = annuityDerivative.getDerivative(0);
    LocalDate settlementDate = ((CashSettlement) swaption.getSwaptionSettlement()).getSettlementDate();
    double discountSettle = ratesProvider.discountFactor(fixedLeg.getCurrency(), settlementDate);
    double strike = calculateStrike(fixedLeg);
    double tenor = swaptionVolatilities.tenor(fixedLeg.getStartDate(), fixedLeg.getEndDate());
    double shift = swaptionVolatilities.shift(expiry, tenor);
    ValueDerivatives volatilityAdj = swaptionVolatilities.volatilityAdjoint(expiry, tenor, strike, forward);
    boolean isCall = fixedLeg.getPayReceive().isPay();
    double shiftedForward = forward + shift;
    double shiftedStrike = strike + shift;
    double price = BlackFormulaRepository.price(shiftedForward, shiftedStrike, expiry, volatilityAdj.getValue(), isCall);
    double delta = BlackFormulaRepository.delta(shiftedForward, shiftedStrike, expiry, volatilityAdj.getValue(), isCall);
    double vega = BlackFormulaRepository.vega(shiftedForward, shiftedStrike, expiry, volatilityAdj.getValue());
    PointSensitivityBuilder forwardSensi = getSwapPricer().parRateSensitivity(underlying, ratesProvider);
    PointSensitivityBuilder discountSettleSensi =
        ratesProvider.discountFactors(fixedLeg.getCurrency()).zeroRatePointSensitivity(settlementDate);
    double sign = swaption.getLongShort().sign();
    return forwardSensi.multipliedBy(
        sign * discountSettle * (annuityCash * (delta + vega * volatilityAdj.getDerivative(0))
            + annuityCashDr * price)).combinedWith(discountSettleSensi.multipliedBy(sign * annuityCash * price));
  }