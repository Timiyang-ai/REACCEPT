public BondFutureOptionSensitivity presentValueSensitivityModelParamsVolatility(
      ResolvedBondFutureOptionTrade futureOptionTrade,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider) {

    ResolvedBondFuture future = futureOptionTrade.getProduct().getUnderlyingFuture();
    double futurePrice = futureOptionPricer.getFuturePricer().price(future, ratesProvider);
    return presentValueSensitivityModelParamsVolatility(futureOptionTrade, ratesProvider, volatilityProvider, futurePrice);
  }