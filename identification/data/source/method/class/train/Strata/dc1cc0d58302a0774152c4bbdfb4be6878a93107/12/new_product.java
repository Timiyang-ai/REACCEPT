public PointSensitivities parSpreadSensitivity(ResolvedIborFutureTrade trade, RatesProvider provider) {
    return productPricer.priceSensitivity(trade.getProduct(), provider);
  }