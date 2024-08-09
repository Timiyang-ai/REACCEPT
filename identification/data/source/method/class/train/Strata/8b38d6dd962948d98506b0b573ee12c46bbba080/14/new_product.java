public double price(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    return productPricer.price(trade.getProduct(), ratesProvider, hwProvider);
  }