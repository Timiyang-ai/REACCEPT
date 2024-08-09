public PointSensitivities presentValueSensitivityRates(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    ResolvedIborFuture product = trade.getProduct();
    PointSensitivities priceSensi = productPricer.priceSensitivityRates(product, ratesProvider, hwProvider);
    PointSensitivities marginIndexSensi = productPricer.marginIndexSensitivity(product, priceSensi);
    return marginIndexSensi.multipliedBy(trade.getQuantity());
  }