public PointSensitivities presentValueSensitivity(ResolvedIborFixingDepositTrade trade, RatesProvider provider) {
    return productPricer.presentValueSensitivity(trade.getProduct(), provider);
  }