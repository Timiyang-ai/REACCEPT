public PointSensitivities parSpreadSensitivity(RatesProvider provider, IborFutureTrade trade) {
    return productPricer.priceSensitivity(provider, trade.getSecurity().getProduct());
  }