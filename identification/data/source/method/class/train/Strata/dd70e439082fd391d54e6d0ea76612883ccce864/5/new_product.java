public double price(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    ResolvedFxSingle underlying = option.getUnderlying();
    double forwardPrice = undiscountedPrice(option, ratesProvider, volatilityProvider);
    double discountFactor = ratesProvider.discountFactor(option.getCounterCurrency(), underlying.getPaymentDate());
    return discountFactor * forwardPrice;
  }