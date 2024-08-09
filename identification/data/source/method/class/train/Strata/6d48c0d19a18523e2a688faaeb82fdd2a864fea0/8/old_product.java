public double theta(
      ResolvedFxSingleBarrierOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    ValueDerivatives priceDerivatives = priceDerivatives(option, ratesProvider, volatilityProvider);
    return -priceDerivatives.getDerivative(5);
  }