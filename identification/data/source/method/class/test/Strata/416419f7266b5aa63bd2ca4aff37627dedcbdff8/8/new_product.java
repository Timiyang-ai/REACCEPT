public BondFutureOptionSensitivity priceSensitivityBlackVolatility(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider) {

    double futurePrice = futurePrice(futureOption, ratesProvider);
    return priceSensitivityBlackVolatility(futureOption, ratesProvider, volatilityProvider, futurePrice);
  }