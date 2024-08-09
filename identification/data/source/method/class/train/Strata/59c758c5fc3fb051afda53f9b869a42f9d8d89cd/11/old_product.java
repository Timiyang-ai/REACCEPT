public PointSensitivities parSpreadSensitivity(IborFixingDepositTrade trade, RatesProvider provider) {
    return productPricer.parSpreadSensitivity(trade.getProduct(), provider);
  }