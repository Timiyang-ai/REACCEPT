public BondFutureOptionSensitivity priceSensitivityBlackVolatility(
      BondFutureOption futureOption,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider) {

    double futurePrice = futurePrice(futureOption, ratesProvider);
    return priceSensitivityBlackVolatility(futureOption, ratesProvider, volatilityProvider, futurePrice);
  }