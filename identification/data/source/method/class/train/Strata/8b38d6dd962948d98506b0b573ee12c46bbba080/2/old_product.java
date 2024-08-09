public PointSensitivities parSpreadSensitivity(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    return productPricer.priceSensitivity(trade.getProduct(), ratesProvider, hwProvider);
  }