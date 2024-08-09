public PointSensitivities parSpreadSensitivity(IborFutureTrade trade, RatesProvider provider) {
    return productPricer.priceSensitivity(trade.getSecurity().getProduct(), provider);
  }