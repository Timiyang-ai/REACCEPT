public PointSensitivities presentValueSensitivity(ResolvedIborFutureTrade trade, RatesProvider ratesProvider) {
    ResolvedIborFuture product = trade.getProduct();
    PointSensitivities priceSensi = productPricer.priceSensitivity(product, ratesProvider);
    PointSensitivities marginIndexSensi = productPricer.marginIndexSensitivity(product, priceSensi);
    return marginIndexSensi.multipliedBy(trade.getQuantity());
  }