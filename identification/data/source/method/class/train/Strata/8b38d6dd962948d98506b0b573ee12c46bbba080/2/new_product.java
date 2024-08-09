public PointSensitivities parSpreadSensitivityRates(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    return productPricer.priceSensitivityRates(trade.getProduct(), ratesProvider, hwProvider);
  }