public PointSensitivities parSpreadSensitivity(ResolvedIborFixingDepositTrade trade, ImmutableRatesProvider provider) {
    return productPricer.parSpreadSensitivity(trade.getProduct(), provider);
  }