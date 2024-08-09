public PointSensitivities presentValueSensitivity(ResolvedIborFixingDepositTrade trade, ImmutableRatesProvider provider) {
    return productPricer.presentValueSensitivity(trade.getProduct(), provider);
  }