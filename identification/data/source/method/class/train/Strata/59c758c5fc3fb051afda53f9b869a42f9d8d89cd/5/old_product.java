public PointSensitivities parSpreadSensitivity(IborFixingDepositTrade trade, ImmutableRatesProvider provider) {
    return productPricer.parSpreadSensitivity(trade.getProduct(), provider);
  }