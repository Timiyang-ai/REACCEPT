public double delta(
      ResolvedFxSingleBarrierOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    if (volatilityProvider.relativeTime(option.getUnderlyingOption().getExpiry()) < 0d) {
      return 0d;
    }
    ValueDerivatives priceDerivatives = priceDerivatives(option, ratesProvider, volatilityProvider);
    return priceDerivatives.getDerivative(0);
  }