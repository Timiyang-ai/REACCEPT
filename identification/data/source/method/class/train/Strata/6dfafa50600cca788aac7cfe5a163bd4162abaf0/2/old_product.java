public double price(ResolvedDsfTrade trade, RatesProvider provider) {
    return productPricer.price(trade.getProduct(), provider);
  }