public PointSensitivities presentValueSensitivity(
      IborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantConvexityFactorProvider hwProvider) {
    IborFuture product = trade.getSecurity().getProduct();
    PointSensitivities priceSensi = productPricer.priceSensitivity(product, ratesProvider, hwProvider);
    PointSensitivities marginIndexSensi = productPricer.marginIndexSensitivity(product, priceSensi);
    return marginIndexSensi.multipliedBy(trade.getQuantity());
  }