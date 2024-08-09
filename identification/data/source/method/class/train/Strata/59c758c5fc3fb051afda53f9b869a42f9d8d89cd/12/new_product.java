public PointSensitivities parSpreadSensitivity(ResolvedIborFixingDepositTrade trade, RatesProvider provider) {
    return productPricer.parSpreadSensitivity(trade.getProduct(), provider);
  }