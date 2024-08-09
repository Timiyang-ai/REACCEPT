public double price(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    FxSingle underlying = option.getUnderlying();
    double forwardPrice = undiscountedPrice(option, ratesProvider, volatilityProvider);
    double discountFactor = ratesProvider.discountFactor(option.getPayoffCurrency(), underlying.getPaymentDate());
    return discountFactor * forwardPrice;
  }