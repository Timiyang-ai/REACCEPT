public PointSensitivities parSpreadSensitivity(
      IborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantConvexityFactorProvider hwProvider) {
    return productPricer.priceSensitivity(trade.getSecurity().getProduct(), ratesProvider, hwProvider);
  }