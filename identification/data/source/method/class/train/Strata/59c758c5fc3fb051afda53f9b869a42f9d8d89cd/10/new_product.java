public double parSpread(ResolvedIborFixingDepositTrade trade, RatesProvider provider) {
    return productPricer.parSpread(trade.getProduct(), provider);
  }