public double theta(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider) {

    double futurePrice = futurePrice(futureOption, ratesProvider);
    return theta(futureOption, ratesProvider, volatilityProvider, futurePrice);
  }