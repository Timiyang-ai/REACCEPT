public double parSpread(ResolvedIborFixingDepositTrade trade, ImmutableRatesProvider provider) {
    return productPricer.parSpread(trade.getProduct(), provider);
  }