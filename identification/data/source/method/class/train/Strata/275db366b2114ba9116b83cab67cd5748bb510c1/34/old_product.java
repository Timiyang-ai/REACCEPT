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
    double strike = getStrike(fixedLeg);
    if (expiry < 0d) { // Option has expired already
      return SwaptionSensitivity.of(swaptionVolatilities.getConvention(), expiryDateTime, tenor, strike, 0d,
          fixedLeg.getCurrency(), 0d);
    }
    double forward = swapPricer.parRate(underlying, ratesProvider);
    double annuityCash = swapPricer.getLegPricer().annuityCash(fixedLeg, forward);
    double discountSettle = ratesProvider.discountFactor(
        fixedLeg.getCurrency(), ((CashSettlement) expanded.getSwaptionSettlement()).getSettlementDate());
    double volatility = swaptionVolatilities.volatility(expiryDateTime, tenor, strike, forward);
    boolean isCall = (fixedLeg.getPayReceive() == PayReceive.PAY);
    NormalFunctionData normalData = NormalFunctionData.of(forward, Math.abs(annuityCash * discountSettle), volatility);
    EuropeanVanillaOption option = EuropeanVanillaOption.of(strike, expiry, isCall ? PutCall.CALL : PutCall.PUT);
    double vega = NORMAL.getVega(option, normalData) * ((expanded.getLongShort() == LongShort.LONG) ? 1d : -1d);
    return SwaptionSensitivity.of(
        swaptionVolatilities.getConvention(), expiryDateTime, tenor, strike, forward, fixedLeg.getCurrency(), vega);
  }