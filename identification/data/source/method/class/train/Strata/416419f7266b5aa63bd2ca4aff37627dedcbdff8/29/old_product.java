public double price(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider) {

    double futurePrice = futurePrice(futureOption, ratesProvider);
    return price(futureOption, ratesProvider, volatilityProvider, futurePrice);
  }