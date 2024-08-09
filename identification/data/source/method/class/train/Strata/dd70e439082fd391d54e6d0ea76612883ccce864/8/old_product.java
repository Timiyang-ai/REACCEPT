public double price(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {
    Fx underlying = option.getUnderlying();
    double forwardPrice = forwardPrice(option, ratesProvider, volatilityProvider);
    double discountFactor =
        ratesProvider.discountFactor(option.getPayoffCurrency(), underlying.getPaymentDate());
    return discountFactor * forwardPrice;
  }