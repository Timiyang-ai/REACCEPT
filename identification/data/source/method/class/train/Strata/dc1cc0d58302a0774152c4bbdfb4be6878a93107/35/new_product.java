public PointSensitivities parSpreadSensitivity(ResolvedIborFutureTrade trade, RatesProvider ratesProvider) {
    return productPricer.priceSensitivity(trade.getProduct(), ratesProvider);
  }