public double price(ResolvedDsfTrade trade, RatesProvider ratesProvider) {
    return productPricer.price(trade.getProduct(), ratesProvider);
  }