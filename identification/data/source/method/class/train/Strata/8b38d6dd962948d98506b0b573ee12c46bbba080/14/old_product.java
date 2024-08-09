public double price(
      IborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {
    return productPricer.price(trade.getSecurity().getProduct(), ratesProvider, hwProvider);
  }