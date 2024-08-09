public double parSpread(IborFixingDepositTrade trade, RatesProvider provider) {
    return productPricer.parSpread(trade.getProduct(), provider);
  }