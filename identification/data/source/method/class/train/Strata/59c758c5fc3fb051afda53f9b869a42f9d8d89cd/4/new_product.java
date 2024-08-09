public PointSensitivities presentValueSensitivity(IborFixingDepositTrade trade, RatesProvider provider) {
    return productPricer.presentValueSensitivity(trade.getProduct(), provider);
  }