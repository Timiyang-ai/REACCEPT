public BondFutureOptionSensitivity priceSensitivityModelParamsVolatility(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider) {

    double futurePrice = futurePrice(futureOption, ratesProvider);
    return priceSensitivityModelParamsVolatility(futureOption, ratesProvider, volatilityProvider, futurePrice);
  }