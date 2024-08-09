public PointSensitivities presentValueSensitivity(IborFixingDepositTrade trade, ImmutableRatesProvider provider) {
    return productPricer.presentValueSensitivity(trade.getProduct(), provider);
  }