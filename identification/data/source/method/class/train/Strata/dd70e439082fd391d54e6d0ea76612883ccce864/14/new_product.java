public double price(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    ResolvedFxSingle underlying = option.getUnderlying();
    double forwardPrice = undiscountedPrice(option, ratesProvider, volatilities);
    double discountFactor = ratesProvider.discountFactor(option.getCounterCurrency(), underlying.getPaymentDate());
    return discountFactor * forwardPrice;
  }