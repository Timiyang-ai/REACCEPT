public double price(ResolvedIborFutureTrade trade, RatesProvider ratesProvider) {
    return productPricer.price(trade.getProduct(), ratesProvider);
  }