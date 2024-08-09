public double parSpread(IborFixingDepositTrade trade, ImmutableRatesProvider provider) {
    return productPricer.parSpread(trade.getProduct(), provider);
  }