public BondFutureOptionSensitivity presentValueSensitivityBlackVolatility(
      ResolvedBondFutureOptionTrade futureOptionTrade,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider) {

    ResolvedBondFuture future = futureOptionTrade.getProduct().getUnderlying();
    double futurePrice = futureOptionPricer.getFuturePricer().price(future, ratesProvider);
    return presentValueSensitivityBlackVolatility(futureOptionTrade, ratesProvider, volatilityProvider, futurePrice);
  }