public double price(ResolvedIborFutureTrade trade, RatesProvider provider) {
    return productPricer.price(trade.getProduct(), provider);
  }