public double price(
      IborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantConvexityFactorProvider hwProvider) {
    return productPricer.price(trade.getSecurity().getProduct(), ratesProvider, hwProvider);
  }