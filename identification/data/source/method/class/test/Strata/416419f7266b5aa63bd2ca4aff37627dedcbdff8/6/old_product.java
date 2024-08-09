public BondFutureOptionSensitivity presentValueSensitivityBlackVolatility(
      BondFutureOptionTrade futureOptionTrade,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider) {

    BondFuture future = futureOptionTrade.getProduct().getUnderlying();
    double futurePrice = futureOptionPricer.getFuturePricer().price(future, ratesProvider);
    return presentValueSensitivityBlackVolatility(futureOptionTrade, ratesProvider, volatilityProvider, futurePrice);
  }