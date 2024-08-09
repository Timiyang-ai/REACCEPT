public PointSensitivities parSpreadSensitivity(
      IborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {
    return productPricer.priceSensitivity(trade.getSecurity().getProduct(), ratesProvider, hwProvider);
  }